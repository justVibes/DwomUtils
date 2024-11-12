package com.example.ui_components.file_modifiers

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.ui_components.models.client.ClientItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class CreateAndSaveWordDoc @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun saveDocument(file: File, mimeType: String) {
        var isSuccessful by mutableStateOf<Boolean?>(null)
        withContext(Dispatchers.IO) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Export isn't possible on this android version. (v.${Build.VERSION.SDK_INT})",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return@withContext
            }
            val resolver = context.contentResolver

            val docDir = MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

            val docMetaData = ContentValues().apply {
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                put(MediaStore.Downloads.DISPLAY_NAME, file.name)
                put(MediaStore.Downloads.MIME_TYPE, mimeType)
                put(MediaStore.Downloads.DATE_ADDED, System.currentTimeMillis())
                put(MediaStore.Downloads.IS_PENDING, 1)
            }

            resolver.insert(docDir, docMetaData)?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.use { outputStream ->
                        resolver.openInputStream(Uri.fromFile(file))?.use { inputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }
                    docMetaData.put(MediaStore.Downloads.IS_PENDING, 0)
                    resolver.update(uri, docMetaData, null, null)
                    isSuccessful = true
                } catch (e: Exception) {
                    e.printStackTrace()
                    resolver.delete(uri, null, null)
                    isSuccessful = false
                }
            }
            launch(Dispatchers.Main) {
                if (isSuccessful == true) {
                    Toast.makeText(context, "Exported Successfully!", Toast.LENGTH_SHORT).show()
                } else if (isSuccessful == false) {
                    Toast.makeText(context, "Failed to export data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun createDocDocument(fileName: String, fileExtension: String, content: String? = null, clientItem: String? = null): File {
        val docFile = File.createTempFile(
            fileName,
            fileExtension,
            context.dataDir
        )

        docFile.appendText(content ?: clientItem ?: "")
        return docFile
    }

    suspend fun createAndSaveDocxDocument(
        fileName: String,
        fileExtension: String,
        content: XWPFDocument? = null,
        clientItem: ClientItem? = null
    ) {
        require(fileName.isNotBlank()) { "File name cannot be blank" }
        require(fileExtension.isNotBlank()) { "File extension cannot be blank" }
        withContext(Dispatchers.IO) {
            try {
                val externalStorageDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val directory = File(externalStorageDir, "mynew_section")
                directory.mkdirs()
                val docFile = File(directory, "$fileName.$fileExtension")

                if (docFile.exists()) {
                    docFile.delete()
                } else {
                    docFile.createNewFile()
                }

                val outputStream = FileOutputStream(docFile)
                when {
                    content != null -> content.write(outputStream)
                    clientItem != null ->{
                        ClientItem.Config.mapToXWPFDoc(form = clientItem).write(outputStream)
                    }
                }
                outputStream.close()

            } catch (e: IOException) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to export document", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
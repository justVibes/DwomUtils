package com.example.ui_components.create_save_file

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.Q)
class CreateSaveFile @Inject constructor(
    @ApplicationContext val context: Context
) {
    suspend fun saveDocument(file: File, mimeType: String) {
        var isSuccessful by mutableStateOf<Boolean?>(null)
        withContext(Dispatchers.IO) {
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
        }
        withContext(Dispatchers.Main){
            if(isSuccessful == true){
                Toast.makeText(context, "Exported Successfully!", Toast.LENGTH_SHORT).show()
            }else if (isSuccessful == false){
                Toast.makeText(context, "Failed to export data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun createDocument(fileName: String, fileExtension: String, content: String): File {
        val docFile = File.createTempFile(
            fileName,
            fileExtension,
            context.cacheDir
        )

        docFile.appendText(content)
        return docFile
    }
}
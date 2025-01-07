package com.example.ui_components.models.user.components.medical_info.components.lab_result

import android.graphics.Bitmap
import com.example.ui_components.models.user.components.medical_info.components.lab_result.variants.LocalLabResult
import com.google.firebase.firestore.Exclude
import java.util.Calendar

data class LabResult(
    var resultType: String = "", /*Use the 'LabResultTypes' enum class to initialize*/
    /*
    * The MAIN intent of this property is to store the download url of EACH lab result's uri
    * (both photo and pdf) saved in firebase storage.
    * + To render photos this 'resultUrl' property can be used as is, just parse it as a uri at run-time
    * + To render pdfs convert this url to a uri, then use the custom made pdfConverter to convert
    *   the uri into a list of bitmaps, then temporarily store that list in 'tempPdf'.
    */
    var resultUrl: String = "",
    var storagePath: String? = null, /*A reference to the location of the 'resultUrl'*/
    val isDeleted: Boolean = false, /* This is used to track all the deleted labResults that live on the server */
    val creationDateTime: Long = Calendar.getInstance().timeInMillis,
    val authorName: String = "",
    val title: String = "",
    @Exclude var tempPdf: List<Bitmap> = emptyList() /*This is for local usage (To render pdfs).*/
) {
    companion object {
        fun mapToLocal(form: LabResult) = LocalLabResult().apply {
            resultType = form.resultType
            resultUrl = form.resultUrl
            storagePath = form.storagePath
            isDeleted = form.isDeleted
            creationDateTime = form.creationDateTime
            authorName = form.authorName
        }
    }
}


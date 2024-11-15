package com.example.ui_components.models.client.components

import android.graphics.Bitmap
import android.net.Uri
import com.google.firebase.firestore.Exclude
import com.google.firebase.storage.StorageReference

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
    var storageRef: StorageReference? = null, /*A reference to the location of the 'resultUrl'*/
    val isDeleted: Boolean = false, /* This is used to track all the lab results that were deleted */
    @Exclude var resultUri: Uri = Uri.EMPTY,
    @Exclude var tempPdf: List<Bitmap> = emptyList() /*This is for local usage (To render pdfs).*/
)

enum class LabResultTypes {
    PDF, PHOTO
}
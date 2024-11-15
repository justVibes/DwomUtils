package com.example.ui_components.models.client.components

import com.google.firebase.storage.StorageReference

data class LabResult(
    var resultType: String = "", /*Use the 'LabResultTypes' enum class to initialize*/
    var resultUrl: String = "",
    var storageRef: StorageReference? = null
)

enum class LabResultTypes{
    PDF, PHOTO
}
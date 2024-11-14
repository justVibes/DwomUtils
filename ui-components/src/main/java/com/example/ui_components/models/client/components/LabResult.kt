package com.example.ui_components.models.client.components

data class LabResult(
    var resultType: String = "", /*Use the 'LabResultTypes' enum class to initialize*/
    var result: String = ""
)

enum class LabResultTypes{
    PDF, PHOTO
}
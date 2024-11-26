package com.example.ui_components.models.core.user.components

import com.google.firebase.firestore.Exclude
import java.util.Calendar

data class PortfolioFile(
    val fileType: String = "",/* Use the 'PortfolioFileType' enum to initialize */
    val fileUrl: String = "",
    val storagePath: String? = null, /* The path to the location of 'fileUrl' */
    val creationDate: String = "${Calendar.getInstance().timeInMillis}",
    @Exclude val isDeleted: Boolean = false
)

enum class PortfolioFileType {
    PHOTO, VIDEO
}

package com.example.ui_components.models.user.components.freelancer_info.components.portfolio

import com.example.ui_components.models.user.components.freelancer_info.components.portfolio.variants.LocalPortfolioFile
import com.google.firebase.firestore.Exclude
import java.util.Calendar

data class PortfolioFile(
    val fileType: String = "",/* Use the 'PortfolioFileType' enum to initialize */
    val fileUrl: String = "",
    val storagePath: String? = null, /* The path to the location of 'fileUrl' */
    val creationDate: Long = Calendar.getInstance().timeInMillis,
    @Exclude val isDeleted: Boolean = false
) {
    companion object {
        fun mapToLocal(form: PortfolioFile) = LocalPortfolioFile().apply {
            val fmtForm = trimmedFields(form)
            fileType = fmtForm.fileType
            fileUrl = fmtForm.fileUrl
            storagePath = fmtForm.storagePath
            creationDate = fmtForm.creationDate
            isDeleted = fmtForm.isDeleted
        }

        fun trimmedFields(form: PortfolioFile) = form.copy(
            fileType = form.fileType.trim(),
            fileUrl = form.fileUrl.trim(),
            storagePath = form.storagePath?.trim(),
            creationDate = form.creationDate,
            isDeleted = form.isDeleted
        )
    }
}



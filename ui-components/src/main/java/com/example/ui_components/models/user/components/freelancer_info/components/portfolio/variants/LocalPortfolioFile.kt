package com.example.ui_components.models.user.components.freelancer_info.components.portfolio.variants

import com.example.ui_components.models.user.components.freelancer_info.components.portfolio.PortfolioFile
import io.realm.kotlin.types.EmbeddedRealmObject
import java.util.Calendar

class LocalPortfolioFile : EmbeddedRealmObject {
    var fileType: String = ""
    var fileUrl: String = ""
    var storagePath: String? = null
    var creationDate: Long = Calendar.getInstance().timeInMillis
    var isDeleted: Boolean = false

    companion object {
        fun mapToOriginal(form: LocalPortfolioFile): PortfolioFile {
            val fmtForm = trimmedFields(form)
            return PortfolioFile(
                fileType = fmtForm.fileType,
                fileUrl = fmtForm.fileUrl,
                storagePath = fmtForm.storagePath,
                creationDate = fmtForm.creationDate,
                isDeleted = fmtForm.isDeleted
            )
        }

        fun trimmedFields(form: LocalPortfolioFile) = LocalPortfolioFile().apply {
            fileType = form.fileType.trim()
            fileUrl = form.fileUrl.trim()
            storagePath = form.storagePath?.trim()
            creationDate = form.creationDate
            isDeleted = form.isDeleted
        }
    }
}
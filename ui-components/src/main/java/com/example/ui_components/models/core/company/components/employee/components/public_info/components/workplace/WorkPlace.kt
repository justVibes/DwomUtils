package com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace

import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.variants.LocalWorkPlace
import kotlinx.serialization.Serializable

@Serializable
data class WorkPlace(
    /* This is only initialized if the specified company has its own collection */
    val companyCollectionPath: String? = null,
    val name: String = "",
    val field: String = "",
    val type: String = ""
) {
    companion object {
        fun mapToLocal(form: WorkPlace) = LocalWorkPlace().apply {
            val fmtForm = trimmedFields(form)
            companyCollectionPath = fmtForm.companyCollectionPath
            name = fmtForm.name
            field = fmtForm.field
            type = fmtForm.type
        }

        fun trimmedFields(form: WorkPlace) = form.copy(
            companyCollectionPath = form.companyCollectionPath?.trim(),
            name = form.name.trim(),
            field = form.field.trim(),
            type = form.type.trim()
        )
    }
}
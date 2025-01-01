package com.example.ui_components.models.core.company.components.employee.variants

import com.example.ui_components.models.client.components.core.name.LocalName
import com.example.ui_components.models.core.company.components.employee.Employee
import com.example.ui_components.models.core.company.components.employee.components.metadata.variants.LocalEmployeeMetadata
import com.example.ui_components.models.core.company.components.employee.components.work_break.variants.LocalWorkBreakConfig
import com.example.ui_components.models.core.company.components.metadata.variants.LocalCompanyMetadata
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalEmployee : EmbeddedRealmObject {
    var name: LocalName? = null
    var email: String = ""
    var photoUrl: String = ""
    var metadata: LocalEmployeeMetadata? = null
    var workBreakConfig: LocalWorkBreakConfig? = null
    var companyMetadata: LocalCompanyMetadata? = null

    companion object {
        fun mapToOriginal(form: LocalEmployee): Employee {
            val fmtForm = trimmedFields(form)
            return Employee(
                name = LocalName.mapToOriginal(fmtForm.name ?: LocalName()),
                email = fmtForm.email,
                photoUrl = fmtForm.photoUrl,
                metadata = LocalEmployeeMetadata.mapToOriginal(
                    fmtForm.metadata ?: LocalEmployeeMetadata()
                ),
                companyMetadata = fmtForm.companyMetadata.let {
                    LocalCompanyMetadata.mapToOriginal(it ?: LocalCompanyMetadata())
                },
                workBreakConfig = fmtForm.workBreakConfig?.let {
                    LocalWorkBreakConfig.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalEmployee) = LocalEmployee().apply {
            name = form.name?.let { LocalName.trimmedFields(it) }
            email = form.email.trim()
            photoUrl = form.photoUrl.trim()
            metadata = form.metadata
            workBreakConfig = form.workBreakConfig
            companyMetadata = form.companyMetadata?.let { LocalCompanyMetadata.trimmedFields(it) }
        }
    }
}
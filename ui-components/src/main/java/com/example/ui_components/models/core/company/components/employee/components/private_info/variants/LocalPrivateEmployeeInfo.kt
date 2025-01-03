package com.example.ui_components.models.core.company.components.employee.components.private_info.variants

import com.example.ui_components.models.core.company.components.employee.components.private_info.PrivateEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.EmployeeMetadata
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.variants.LocalEmployeeMetadata
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.work_break.variants.LocalWorkBreakConfig
import com.example.ui_components.models.core.company.components.metadata.CompanyMetadata
import com.example.ui_components.models.core.company.components.metadata.variants.LocalCompanyMetadata
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPrivateEmployeeInfo : EmbeddedRealmObject {
    var metadata: LocalEmployeeMetadata? = null
    var companyMetadata: LocalCompanyMetadata? = null
    var workBreakConfig: LocalWorkBreakConfig? = null

    companion object {
        fun mapToOriginal(form: LocalPrivateEmployeeInfo): PrivateEmployeeInfo {
            val fmtForm = trimmedFields(form)
            return PrivateEmployeeInfo(
                metadata = fmtForm.metadata?.let { LocalEmployeeMetadata.mapToOriginal(it) }
                    ?: EmployeeMetadata(),
                companyMetadata = fmtForm.companyMetadata?.let {
                    LocalCompanyMetadata.mapToOriginal(it)
                } ?: CompanyMetadata(),
                workBreakConfig = fmtForm.workBreakConfig?.let {
                    LocalWorkBreakConfig.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalPrivateEmployeeInfo) = LocalPrivateEmployeeInfo().apply {
            metadata = form.metadata
            workBreakConfig = form.workBreakConfig
            companyMetadata = form.companyMetadata?.let { LocalCompanyMetadata.trimmedFields(it) }
        }
    }
}
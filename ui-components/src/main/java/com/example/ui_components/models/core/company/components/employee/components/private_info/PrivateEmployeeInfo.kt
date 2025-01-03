package com.example.ui_components.models.core.company.components.employee.components.private_info

import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.EmployeeMetadata
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.work_break.WorkBreakConfig
import com.example.ui_components.models.core.company.components.employee.components.private_info.variants.LocalPrivateEmployeeInfo
import com.example.ui_components.models.core.company.components.metadata.CompanyMetadata

data class PrivateEmployeeInfo(
    val metadata: EmployeeMetadata = EmployeeMetadata(),
    val companyMetadata: CompanyMetadata = CompanyMetadata(),
    val workBreakConfig: WorkBreakConfig? = null,
) {

    companion object {
        fun mapToLocal(form: PrivateEmployeeInfo) = LocalPrivateEmployeeInfo().apply {
            val fmtForm = trimmedFields(form)
            metadata = EmployeeMetadata.mapToLocal(fmtForm.metadata)
            workBreakConfig = fmtForm.workBreakConfig?.let { WorkBreakConfig.mapToLocal(it) }
            companyMetadata = fmtForm.companyMetadata.let { CompanyMetadata.mapToLocal(it) }
        }

        fun trimmedFields(form: PrivateEmployeeInfo) = form.copy(
            metadata = form.metadata,
            workBreakConfig = form.workBreakConfig,
            companyMetadata = form.companyMetadata.let { CompanyMetadata.trimmedFields(it) }
        )
    }
}
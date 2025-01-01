package com.example.ui_components.models.core.company.components.employee

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.core.company.components.employee.components.metadata.EmployeeMetadata
import com.example.ui_components.models.core.company.components.employee.components.work_break.WorkBreakConfig
import com.example.ui_components.models.core.company.components.employee.variants.LocalEmployee
import com.example.ui_components.models.core.company.components.metadata.CompanyMetadata


data class Employee(
    val name: Name = Name(),
    val email: String = "",
    val photoUrl: String = "",
    val metadata: EmployeeMetadata = EmployeeMetadata(),
    val companyMetadata: CompanyMetadata = CompanyMetadata(),
    val workBreakConfig: WorkBreakConfig? = null,
) {

    companion object {
        fun mapToLocal(form: Employee) = LocalEmployee().apply {
            val fmtForm = trimmedFields(form)
            email = fmtForm.email
            name = Name.mapToLocal(fmtForm.name)
            photoUrl = fmtForm.photoUrl
            metadata = EmployeeMetadata.mapToLocal(fmtForm.metadata)
            workBreakConfig = fmtForm.workBreakConfig?.let { WorkBreakConfig.mapToLocal(it) }
            companyMetadata = fmtForm.companyMetadata.let { CompanyMetadata.mapToLocal(it) }
        }

        fun trimmedFields(form: Employee) = form.copy(
            email = form.email.trim(),
            name = Name.trimmedFields(form.name),
            photoUrl = form.photoUrl.trim(),
            metadata = form.metadata,
            workBreakConfig = form.workBreakConfig,
            companyMetadata = form.companyMetadata.let { CompanyMetadata.trimmedFields(it) }
        )
    }
}


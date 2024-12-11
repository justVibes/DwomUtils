package com.example.ui_components.models.core.company.components.employee.employee_info

import com.example.ui_components.models.core.company.components.company_summary.CompanySummary
import com.example.ui_components.models.core.company.components.employee.employee_info.variants.LocalEmployeeInfo

data class EmployeeInfo(
    val abvEmployeeTitle: String = "",
    val formattedEmployeeTitle: String = "",
    val companySummary: CompanySummary? = null,
    val employeeDocPath: String? = null
) {
    object Config {
        fun mapToLocal(form: EmployeeInfo) = LocalEmployeeInfo().apply {
            val formattedForm = trimmedFields(form)
            abvEmployeeTitle = formattedForm.abvEmployeeTitle
            formattedEmployeeTitle = formattedForm.formattedEmployeeTitle
            companySummary = CompanySummary.Config.mapToLocal(formattedForm.companySummary)
            employeeDocPath = formattedForm.employeeDocPath
        }

        fun trimmedFields(form: EmployeeInfo) = form.copy(
            abvEmployeeTitle = form.abvEmployeeTitle.trim(),
            formattedEmployeeTitle = form.formattedEmployeeTitle.trim(),
            companySummary = CompanySummary.Config.trimmedFields(form.companySummary),
            employeeDocPath = form.employeeDocPath?.trim()
        )
    }
}

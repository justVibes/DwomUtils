package com.example.ui_components.models.core.company.components.employee.employee_info.variants

import com.example.ui_components.models.core.company.components.company_summary.variants.LocalCompanySummary
import com.example.ui_components.models.core.company.components.employee.employee_info.EmployeeInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalEmployeeInfo : EmbeddedRealmObject {
    var abvEmployeeTitle: String = ""
    var formattedEmployeeTitle: String = ""
    var companySummary: LocalCompanySummary? = null
    var employeeDocPath: String? = null

    object Config {
        fun mapToOriginal(form: LocalEmployeeInfo): EmployeeInfo {
            val formattedForm = trimmedFields(form)
            return EmployeeInfo(
                abvEmployeeTitle = formattedForm.abvEmployeeTitle,
                formattedEmployeeTitle = formattedForm.formattedEmployeeTitle,
                companySummary = LocalCompanySummary.Config.mapToOriginal(
                    formattedForm.companySummary ?: LocalCompanySummary()
                ),
                employeeDocPath = formattedForm.employeeDocPath
            )
        }

        fun trimmedFields(form: LocalEmployeeInfo) = LocalEmployeeInfo().apply {
            abvEmployeeTitle = form.abvEmployeeTitle.trim()
            formattedEmployeeTitle = form.formattedEmployeeTitle.trim()
            companySummary = LocalCompanySummary.Config.trimmedFields(
                form.companySummary ?: LocalCompanySummary()
            )
            employeeDocPath = form.employeeDocPath?.trim()
        }
    }
} 
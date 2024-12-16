package com.example.ui_components.models.core.company.components.employee.employee_info.variants

import com.example.ui_components.models.core.company.components.company_summary.variants.LocalCompanySummary
import com.example.ui_components.models.core.company.components.employee.employee_info.EmployeeInfo
import com.example.ui_components.models.core.type_of_worker.components.worker_title.variants.LocalWorkerTitle
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalEmployeeInfo : EmbeddedRealmObject {
    var title: LocalWorkerTitle? = null
    var nextAvailable: Long = 0L
    var nextBreak: Long = 0L
    var companySummary: LocalCompanySummary? = null
    var employeeDocPath: String? = null

    object Config {
        fun mapToOriginal(form: LocalEmployeeInfo): EmployeeInfo {
            val formattedForm = trimmedFields(form)
            return EmployeeInfo(
                title = LocalWorkerTitle.Config.mapToOriginal(
                    formattedForm.title ?: LocalWorkerTitle()
                ),
                nextBreak = formattedForm.nextBreak,
                nextAvailable = formattedForm.nextAvailable,
                companySummary = LocalCompanySummary.Config.mapToOriginal(
                    formattedForm.companySummary ?: LocalCompanySummary()
                ),
                employeeDocPath = formattedForm.employeeDocPath
            )
        }

        fun trimmedFields(form: LocalEmployeeInfo) = LocalEmployeeInfo().apply {
            title = form.title?.let { LocalWorkerTitle.Config.trimmedFields(it) }
            nextBreak = form.nextBreak
            nextAvailable = form.nextAvailable
            companySummary = LocalCompanySummary.Config.trimmedFields(
                form.companySummary ?: LocalCompanySummary()
            )
            employeeDocPath = form.employeeDocPath?.trim()
        }
    }
} 
package com.example.ui_components.models.core.company.components.employee_info

import com.example.ui_components.models.core.company.components.company_summary.CompanySummary
import com.example.ui_components.models.core.company.components.employee_info.variants.LocalEmployeeInfo
import com.example.ui_components.models.core.type_of_worker.components.worker_title.WorkerTitle

data class EmployeeInfo(
    val title: WorkerTitle = WorkerTitle(),
    val nextAvailable: Long = 0L,
    val nextBreak: Long = 0L,
    val companySummary: CompanySummary? = null,
    val employeeDocPath: String? = null
) {
    companion object  {
        fun mapToLocal(form: EmployeeInfo) = LocalEmployeeInfo().apply {
            val formattedForm = trimmedFields(form)
            title = WorkerTitle.mapToLocal(formattedForm.title)
            nextAvailable = formattedForm.nextAvailable
            nextBreak = formattedForm.nextBreak
            companySummary = CompanySummary.mapToLocal(formattedForm.companySummary)
            employeeDocPath = formattedForm.employeeDocPath
        }

        fun trimmedFields(form: EmployeeInfo) = form.copy(
            title = WorkerTitle.trimmedFields(form.title),
            nextBreak = form.nextBreak,
            nextAvailable = form.nextAvailable,
            companySummary = CompanySummary.trimmedFields(form.companySummary),
            employeeDocPath = form.employeeDocPath?.trim()
        )
    }
}

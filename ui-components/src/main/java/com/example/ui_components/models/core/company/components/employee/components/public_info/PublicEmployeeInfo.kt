package com.example.ui_components.models.core.company.components.employee.components.public_info

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.core.company.components.employee.components.public_info.variants.LocalPublicEmployeeInfo
import com.example.ui_components.models.core.worker_title.WorkerTitle
import io.realm.kotlin.ext.toRealmList

data class PublicEmployeeInfo(
    /* Use the 'CompanyFields.{companyField}.Titles' enum to initialize
    * {companyField} == this.companyMetadata.field */
    val title: WorkerTitle = WorkerTitle(),
    val name: Name = Name(),
    val email: String = "",
    val photoUrl: String = "",
    val titleDocPath: String = "",
    val scheduledAppointmentDates: List<Long> = emptyList()
) {

    companion object {
        fun mapToLocal(form: PublicEmployeeInfo) = LocalPublicEmployeeInfo().apply {
            val fmtForm = trimmedFields(form)
            name = fmtForm.name.let { Name.mapToLocal(it) }
            email = fmtForm.email
            photoUrl = fmtForm.photoUrl
            title = fmtForm.title.let { WorkerTitle.mapToLocal(it) }
            titleDocPath = fmtForm.titleDocPath
            scheduledAppointmentDates = fmtForm.scheduledAppointmentDates.toRealmList()
        }

        fun trimmedFields(form: PublicEmployeeInfo) = form.copy(
            name = form.name.let { Name.trimmedFields(it) },
            email = form.email.trim(),
            photoUrl = form.photoUrl.trim(),
            title = form.title.let { WorkerTitle.trimmedFields(it) },
            titleDocPath = form.titleDocPath.trim(),
            scheduledAppointmentDates = form.scheduledAppointmentDates
        )
    }
}
package com.example.ui_components.models.core.company.components.employee.components.public_info

import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.WorkPlace
import com.example.ui_components.models.core.company.components.employee.components.public_info.variants.LocalPublicEmployeeInfo
import com.example.ui_components.models.core.name.Name
import com.example.ui_components.models.core.worker_title.WorkTitle
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable


@Serializable
data class PublicEmployeeInfo(
    /* Use the 'CompanyFields.{companyField}.Titles' enum to initialize
    * {companyField} == this.companyMetadata.field */
    val title: WorkTitle = WorkTitle(),
    val titleDocPath: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val name: Name = Name(),
    val workPlace: WorkPlace = WorkPlace(),
    val scheduledAppointmentDates: List<Long> = emptyList()
) {

    companion object {
        fun mapToLocal(form: PublicEmployeeInfo) = LocalPublicEmployeeInfo().apply {
            val fmtForm = trimmedFields(form)
            name = fmtForm.name.let { Name.mapToLocal(it) }
            email = fmtForm.email
            photoUrl = fmtForm.photoUrl
            title = fmtForm.title.let { WorkTitle.mapToLocal(it) }
            workPlace = fmtForm.workPlace.let { WorkPlace.mapToLocal(it) }
            titleDocPath = fmtForm.titleDocPath
            scheduledAppointmentDates = fmtForm.scheduledAppointmentDates.toRealmList()
        }

        fun trimmedFields(form: PublicEmployeeInfo) = form.copy(
            name = form.name.let { Name.trimmedFields(it) },
            email = form.email.trim(),
            photoUrl = form.photoUrl.trim(),
            title = form.title.let { WorkTitle.trimmedFields(it) },
            workPlace = form.workPlace.let { WorkPlace.trimmedFields(it) },
            titleDocPath = form.titleDocPath.trim(),
            scheduledAppointmentDates = form.scheduledAppointmentDates
        )
    }
}
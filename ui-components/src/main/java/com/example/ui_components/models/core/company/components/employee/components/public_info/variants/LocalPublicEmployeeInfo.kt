package com.example.ui_components.models.core.company.components.employee.components.public_info.variants

import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.WorkPlace
import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.variants.LocalWorkPlace
import com.example.ui_components.models.core.name.Name
import com.example.ui_components.models.core.name.variants.LocalName
import com.example.ui_components.models.core.worker_title.WorkTitle
import com.example.ui_components.models.core.worker_title.variants.LocalWorkTitle
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalPublicEmployeeInfo : EmbeddedRealmObject {
    var titleDocPath: String = ""
    var photoUrl: String = ""
    var email: String = ""
    var name: LocalName? = null
    var title: LocalWorkTitle? = null
    var workPlace: LocalWorkPlace? = null
    var scheduledAppointmentDates: RealmList<Long> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalPublicEmployeeInfo): PublicEmployeeInfo {
            val fmtForm = trimmedFields(form)
            return PublicEmployeeInfo(
                name = fmtForm.name?.let { LocalName.mapToOriginal(it) } ?: Name(),
                email = fmtForm.email,
                photoUrl = fmtForm.photoUrl,
                title = fmtForm.title?.let { LocalWorkTitle.mapToOriginal(it) } ?: WorkTitle(),
                titleDocPath = fmtForm.titleDocPath,
                workPlace = fmtForm.workPlace?.let { LocalWorkPlace.mapToOriginal(it) }
                    ?: WorkPlace(),
                scheduledAppointmentDates = fmtForm.scheduledAppointmentDates.toList()
            )
        }

        fun trimmedFields(form: LocalPublicEmployeeInfo) = LocalPublicEmployeeInfo().apply {
            name = form.name?.let { LocalName.trimmedFields(it) }
            email = form.email.trim()
            photoUrl = form.photoUrl.trim()
            title = form.title?.let { LocalWorkTitle.trimmedFields(it) }
            titleDocPath = form.titleDocPath.trim()
            workPlace = form.workPlace?.let { LocalWorkPlace.trimmedFields(it) }
            scheduledAppointmentDates = form.scheduledAppointmentDates
        }
    }
}
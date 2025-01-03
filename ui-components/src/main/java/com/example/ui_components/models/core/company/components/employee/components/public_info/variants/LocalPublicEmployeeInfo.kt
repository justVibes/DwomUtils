package com.example.ui_components.models.core.company.components.employee.components.public_info.variants

import com.example.ui_components.models.client.components.core.name.LocalName
import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.core.worker_title.WorkerTitle
import com.example.ui_components.models.core.worker_title.variants.LocalWorkerTitle
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalPublicEmployeeInfo : EmbeddedRealmObject {
    var name: LocalName? = null
    var email: String = ""
    var photoUrl: String = ""
    var title: LocalWorkerTitle? = null
    var titleDocPath: String = ""
    var scheduledAppointmentDates: RealmList<Long> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalPublicEmployeeInfo): PublicEmployeeInfo {
            val fmtForm = trimmedFields(form)
            return PublicEmployeeInfo(
                name = fmtForm.name?.let { LocalName.mapToOriginal(it) } ?: Name(),
                email = fmtForm.email,
                photoUrl = fmtForm.photoUrl,
                title = fmtForm.title?.let { LocalWorkerTitle.mapToOriginal(it) } ?: WorkerTitle(),
                titleDocPath = fmtForm.titleDocPath,
                scheduledAppointmentDates = fmtForm.scheduledAppointmentDates.toList()
            )
        }

        fun trimmedFields(form: LocalPublicEmployeeInfo) = LocalPublicEmployeeInfo().apply {
            name = form.name?.let { LocalName.trimmedFields(it) }
            email = form.email.trim()
            photoUrl = form.photoUrl.trim()
            title = form.title?.let { LocalWorkerTitle.trimmedFields(it) }
            titleDocPath = form.titleDocPath.trim()
            scheduledAppointmentDates = form.scheduledAppointmentDates
        }
    }
}
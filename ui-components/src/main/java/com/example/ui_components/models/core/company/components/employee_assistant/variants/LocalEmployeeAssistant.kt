package com.example.ui_components.models.core.company.components.employee_assistant.variants

import com.example.ui_components.models.client.components.core.name.LocalName
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.example.ui_components.models.core.company.components.employee_assistant.EmployeeAssistant
import com.example.ui_components.models.core.company.components.employee_info.variants.LocalEmployeeInfo
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalEmployeeAssistant : EmbeddedRealmObject {
    var email: String = ""
    var name: LocalName? = null
    var photoUrl: String = ""
    var info: LocalEmployeeInfo? = null

    /*This is for local usage*/
    var bookedAppointments: RealmList<LocalBookedAppointment> = realmListOf()

    object Config {
        fun mapToOriginal(form: LocalEmployeeAssistant): EmployeeAssistant {
            val formattedForm = trimmedFields(form)
            return EmployeeAssistant(
                email = formattedForm.email,
                name = LocalName.Config.mapToOriginal(formattedForm.name ?: LocalName()),
                photoUrl = formattedForm.photoUrl,
                info = formattedForm.info?.let { LocalEmployeeInfo.Config.mapToOriginal(it) },
                bookedAppointments = formattedForm.bookedAppointments.map {
                    LocalBookedAppointment.Config.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalEmployeeAssistant) = LocalEmployeeAssistant().apply {
            email = form.email.trim()
            name = form.name?.let { LocalName.Config.trimmedFields(it) }
            photoUrl = form.photoUrl.trim()
            info = form.info?.let { LocalEmployeeInfo.Config.trimmedFields(it) }
            bookedAppointments = form.bookedAppointments
                .map { LocalBookedAppointment.Config.trimmedFields(it) }
                .toRealmList()
        }
    }
}
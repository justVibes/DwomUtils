package com.example.ui_components.models.core.company.components.employee.variants

import com.example.ui_components.models.client.components.core.name.LocalName
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.example.ui_components.models.core.company.components.employee.Employee
import com.example.ui_components.models.core.company.components.employee_assistant.variants.LocalEmployeeAssistant
import com.example.ui_components.models.core.company.components.employee_info.variants.LocalEmployeeInfo
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalEmployee : EmbeddedRealmObject {
    var email: String = ""
    var name: LocalName? = null
    var photoUrl: String = ""
    var info: LocalEmployeeInfo? = null
    var assistant: LocalEmployeeAssistant? = null
    var bookedAppointments: RealmList<LocalBookedAppointment> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalEmployee): Employee {
            val formattedForm = trimmedFields(form)
            return Employee(
                email = formattedForm.email,
                name = LocalName.mapToOriginal(formattedForm.name ?: LocalName()),
                photoUrl = formattedForm.photoUrl,
                info = formattedForm.info?.let { LocalEmployeeInfo.mapToOriginal(it) },
                assistant = formattedForm.assistant?.let {
                    LocalEmployeeAssistant.mapToOriginal(it)
                },
                bookedAppointments = formattedForm.bookedAppointments.map {
                    LocalBookedAppointment.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalEmployee) = LocalEmployee().apply {
            email = form.email.trim()
            name = form.name?.let { LocalName.trimmedFields(it) }
            photoUrl = form.photoUrl.trim()
            info = form.info?.let { LocalEmployeeInfo.trimmedFields(it) }
            assistant = form.assistant?.let { LocalEmployeeAssistant.trimmedFields(it) }
            bookedAppointments =
                form.bookedAppointments.map { LocalBookedAppointment.trimmedFields(it) }
                    .toRealmList()

        }
    }
}
package com.example.ui_components.models.core.company.components.book_appointment.variants

import com.example.ui_components.models.client.variants.LocalClientItem
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalBookedAppointment : EmbeddedRealmObject {
    var date: Long = 0L
    var time: Long = 0L
    var approxDurationInMins: Int = 0
    var employeeDocPath: String? = null
    var clientDocPath: String? = null
    var client: LocalClientItem? = null/*This is for local usage*/

    object Config {
        fun mapToOriginal(form: LocalBookedAppointment): BookedAppointment {
            val formattedForm = trimmedFields(form)
            return BookedAppointment(
                date = form.date,
                time = form.time,
                approxDurationInMins = form.approxDurationInMins,
                employeeDocPath = formattedForm.employeeDocPath,
                clientDocPath = formattedForm.clientDocPath,
                client = formattedForm.client?.let { LocalClientItem.Config.mapToOriginal(it) }
            )
        }

        fun trimmedFields(form: LocalBookedAppointment) = LocalBookedAppointment().apply {
            employeeDocPath = form.employeeDocPath?.trim()
            clientDocPath = form.clientDocPath?.trim()
            client = form.client?.let { LocalClientItem.Config.trimmedFields(it) }
        }
    }
}
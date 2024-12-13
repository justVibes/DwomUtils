package com.example.ui_components.models.core.company.components.book_appointment.variants

import com.example.ui_components.models.client.components.service_provider.LocalServiceProvider
import com.example.ui_components.models.client.variants.LocalClientItem
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalBookedAppointment : EmbeddedRealmObject {
    var date: Long = 0L
    var time: Long = 0L
    var approxStartTime: Long = 0L
    var approxDurationInMins: Int = 0
    var delayInMins: Int = 0
    var advanceInMins: Int = 0
    var serviceProvider: LocalServiceProvider? = null
    var employeeDocPath: String? = null
    var clientDocPath: String? = null
    var client: LocalClientItem? = null/*This is for local usage*/

    object Config {
        fun mapToOriginal(form: LocalBookedAppointment): BookedAppointment {
            val formattedForm = trimmedFields(form)
            return BookedAppointment(
                date = form.date,
                time = form.time,
                approxStartTime = form.approxStartTime,
                approxDurationInMins = form.approxDurationInMins,
                delayInMins = form.delayInMins,
                advanceInMins = form.advanceInMins,
                serviceProvider = formattedForm.serviceProvider?.let {
                    LocalServiceProvider.Config.mapToOriginal(it)
                },
                employeeDocPath = formattedForm.employeeDocPath,
                clientDocPath = formattedForm.clientDocPath,
                client = formattedForm.client?.let { LocalClientItem.Config.mapToOriginal(it) }
            )
        }

        fun trimmedFields(form: LocalBookedAppointment) = LocalBookedAppointment().apply {
            employeeDocPath = form.employeeDocPath?.trim()
            clientDocPath = form.clientDocPath?.trim()
            client = form.client?.let { LocalClientItem.Config.trimmedFields(it) }
            serviceProvider = form.serviceProvider?.let {
                LocalServiceProvider.Config.trimmedFields(it)
            }
        }
    }
}
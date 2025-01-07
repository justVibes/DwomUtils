package com.example.ui_components.models.core.company.components.book_appointment.variants

import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.ClientBill
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants.LocalClientBill
import com.example.ui_components.models.core.company.components.book_appointment.components.metadata.BookedAppointmentMetadata
import com.example.ui_components.models.core.company.components.book_appointment.components.metadata.variants.LocalBookedAppointmentMetadata
import com.example.ui_components.models.core.service_provider.variants.LocalServiceProvider
import com.example.ui_components.models.user.components.core.summary.variants.LocalUserSummary
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalBookedAppointment : EmbeddedRealmObject {
    var serviceProvider: LocalServiceProvider? = null
    var assistantServiceProvider: LocalServiceProvider? = null
    var appointmentReason: String = ""
    var metadata: LocalBookedAppointmentMetadata? = null
    var clientSummary: LocalUserSummary? = null
    var bill: LocalClientBill? = null

    companion object {
        fun mapToOriginal(form: LocalBookedAppointment): BookedAppointment {
            val fmtForm = trimmedFields(form)
            return BookedAppointment(
                appointmentReason = fmtForm.appointmentReason,
                serviceProvider = fmtForm.serviceProvider?.let {
                    LocalServiceProvider.mapToOriginal(it)
                },
                assistantServiceProvider = fmtForm.assistantServiceProvider?.let {
                    LocalServiceProvider.mapToOriginal(it)
                },
                userSummary = fmtForm.clientSummary?.let { LocalUserSummary.mapToOriginal(it) },
                bill = fmtForm.bill?.let { LocalClientBill.mapToOriginal(it) } ?: ClientBill(),
                metadata = fmtForm.metadata?.let { LocalBookedAppointmentMetadata.mapToOriginal(it) }
                    ?: BookedAppointmentMetadata()
            )
        }

        fun trimmedFields(form: LocalBookedAppointment) = LocalBookedAppointment().apply {
            appointmentReason = form.appointmentReason.trim()
            clientSummary = form.clientSummary?.let { LocalUserSummary.trimmedFields(it) }
            serviceProvider = form.serviceProvider?.let { LocalServiceProvider.trimmedFields(it) }
            assistantServiceProvider =
                form.assistantServiceProvider?.let { LocalServiceProvider.trimmedFields(it) }
            metadata = form.metadata?.let { LocalBookedAppointmentMetadata.trimmedFields(it) }
            bill = form.bill?.let { LocalClientBill.trimmedFields(it) }
        }
    }
}
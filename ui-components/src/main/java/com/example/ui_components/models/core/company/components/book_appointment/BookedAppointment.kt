package com.example.ui_components.models.core.company.components.book_appointment

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.ClientBill
import com.example.ui_components.models.core.company.components.book_appointment.components.metadata.BookedAppointmentMetadata
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.example.ui_components.models.core.service_provider.ServiceProvider
import com.example.ui_components.models.user.components.core.summary.UserSummary


data class BookedAppointment(
    val serviceProvider: ServiceProvider? = null,
    val assistantServiceProvider: ServiceProvider? = null,
    val appointmentReason: String = "",
    val metadata: BookedAppointmentMetadata = BookedAppointmentMetadata(),
    /* This is an identifier when the appointments collection is queried by a receptionist */
    val userSummary: UserSummary? = null/*This is for local usage*/,
    val bill: ClientBill = ClientBill()
) {
    companion object {
        fun mapToLocal(form: BookedAppointment) = LocalBookedAppointment().apply {
            val fmtForm = trimmedFields(form)
            appointmentReason = fmtForm.appointmentReason
            serviceProvider = fmtForm.serviceProvider?.let {
                ServiceProvider.mapToLocal(it)
            }
            assistantServiceProvider = fmtForm.assistantServiceProvider?.let {
                ServiceProvider.mapToLocal(it)
            }
            clientSummary = fmtForm.userSummary?.let { UserSummary.mapToLocal(it) }
        }

        fun trimmedFields(form: BookedAppointment) = form.copy(
            appointmentReason = form.appointmentReason.trim(),
            userSummary = form.userSummary?.let { UserSummary.trimmedFields(it) },
            assistantServiceProvider = form.assistantServiceProvider?.let {
                ServiceProvider.trimmedFields(it)
            },
            serviceProvider = form.assistantServiceProvider?.let { ServiceProvider.trimmedFields(it) }
        )
    }
}

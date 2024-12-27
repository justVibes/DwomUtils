package com.example.ui_components.models.core.company.components.book_appointment

import com.example.ui_components.models.client.components.service_provider.ServiceProvider
import com.example.ui_components.models.client.components.summary.ClientSummary
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.ClientBill
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment


data class BookedAppointment(
    val type: String = "", /* Use the 'BookedAppointmentType' enum to initialize */
    val appointmentReason: String = "",
    val positionInQueue: String = "",
    val status: String = "", /* Use 'BookedAppointmentStatus' enum to initialize */
    val approxStartTime: Long = 0L,
    val approxDurationInMins: Int = 0,
    val delayInMins: Int = 0,
    val advanceInMins: Int = 0,
    val companyCollectionPath: String? = null,
    val serviceProvider: ServiceProvider? = null,
    val assistantServiceProvider: ServiceProvider? = null,
    /* This is an identifier when the appointments collection is queried by a receptionist */
    val clientSummary: ClientSummary? = null/*This is for local usage*/,
    val bill: ClientBill = ClientBill()
) {
    companion object {
        fun mapToLocal(form: BookedAppointment) = LocalBookedAppointment().apply {
            val fmtForm = trimmedFields(form)
            type = fmtForm.type
            appointmentReason = fmtForm.appointmentReason
            status = fmtForm.status
            positionInQueue = fmtForm.positionInQueue
            approxDurationInMins = form.approxDurationInMins
            approxStartTime = form.approxStartTime
            delayInMins = form.delayInMins
            advanceInMins = form.advanceInMins
            companyCollectionPath = fmtForm.companyCollectionPath
            serviceProvider = fmtForm.serviceProvider?.let {
                ServiceProvider.mapToLocal(it)
            }
            assistantServiceProvider = fmtForm.assistantServiceProvider?.let {
                ServiceProvider.mapToLocal(it)
            }
            clientSummary = fmtForm.clientSummary?.let { ClientSummary.mapToLocal(it) }
        }

        fun trimmedFields(form: BookedAppointment) = form.copy(
            type = form.type.trim(),
            appointmentReason = form.appointmentReason.trim(),
            positionInQueue = form.positionInQueue.trim(),
            companyCollectionPath = form.companyCollectionPath?.trim(),
            status = form.status.trim(),
            clientSummary = form.clientSummary?.let { ClientSummary.trimmedFields(it) },
            assistantServiceProvider = form.assistantServiceProvider?.let {
                ServiceProvider.trimmedFields(it)
            },
            serviceProvider = form.assistantServiceProvider?.let { ServiceProvider.trimmedFields(it) }
        )
    }
}

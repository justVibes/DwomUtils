package com.example.ui_components.models.core.company.components.book_appointment

import com.example.ui_components.models.client.ClientItem
import com.example.ui_components.models.client.components.service_provider.ServiceProvider
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.google.firebase.firestore.Exclude

data class BookedAppointment(
    val approxStartTime: Long = 0L,
    val approxDurationInMins: Int = 0,
    val delayInMins: Int = 0,
    val advanceInMins: Int = 0,
    val serviceProvider: ServiceProvider? = null,
    /* This is an identifier when the appointments collection is queried by a receptionist */
    val companyCollectionPath: String? = null,
    val clientDocPath: String? = null,
    @Exclude val client: ClientItem? = null/*This is for local usage*/
) {
    object Config {
        fun mapToLocal(form: BookedAppointment) = LocalBookedAppointment().apply {
            val formattedForm = trimmedFields(form)
            approxDurationInMins = form.approxDurationInMins
            approxStartTime = form.approxStartTime
            delayInMins = form.delayInMins
            advanceInMins = form.advanceInMins
            serviceProvider = formattedForm.serviceProvider?.let {
                ServiceProvider.Config.mapToLocal(it)
            }
            employeeDocPath = formattedForm.companyCollectionPath
            clientDocPath = formattedForm.clientDocPath
            client = formattedForm.client?.let { ClientItem.Config.mapToLocal(it) }
        }

        fun trimmedFields(form: BookedAppointment) = form.copy(
            companyCollectionPath = form.companyCollectionPath?.trim(),
            clientDocPath = form.clientDocPath?.trim(),
            client = form.client?.let { ClientItem.Config.trimmedFields(it) },
            serviceProvider = form.serviceProvider?.let { ServiceProvider.Config.trimmedFields(it) }
        )
    }
}

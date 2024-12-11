package com.example.ui_components.models.core.company.components.book_appointment

import com.example.ui_components.models.client.ClientItem
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.google.firebase.firestore.Exclude
import org.jetbrains.annotations.ApiStatus.Experimental

data class BookedAppointment(
    val date: Long = 0L,
    val time: String = "",
    val approxDurationInMins: Int = 0,
    @Experimental val employeeDocPath: String? = null,
    val clientDocPath: String? = null,
    @Exclude val client: ClientItem? = null/*This is for local usage*/
) {
    object Config {
        fun mapToLocal(form: BookedAppointment) = LocalBookedAppointment().apply {
            val formattedForm = trimmedFields(form)
            date = form.date
            time = formattedForm.time
            approxDurationInMins = form.approxDurationInMins
            employeeDocPath = formattedForm.employeeDocPath
            clientDocPath = formattedForm.clientDocPath
            client = formattedForm.client?.let { ClientItem.Config.mapToLocal(it) }
        }

        fun trimmedFields(form: BookedAppointment) = form.copy(
            time = form.time.trim(),
            employeeDocPath = form.employeeDocPath?.trim(),
            clientDocPath = form.clientDocPath?.trim(),
            client = form.client?.let { ClientItem.Config.trimmedFields(it) }
        )
    }
}

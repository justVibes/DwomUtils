package com.example.ui_components.models.client.components.history

import com.example.ui_components.models.client.ClientItem
import com.example.ui_components.models.client.components.emergency_contact_info.EmergencyContactInfo
import com.example.ui_components.models.client.components.history.variants.LocalClientHistory
import com.example.ui_components.models.client.components.info.ClientInfo
import com.example.ui_components.models.client.components.lab_result.LabResult
import com.example.ui_components.models.client.components.note.ClientNote
import com.example.ui_components.models.client.components.service_provider.ServiceProvider
import com.example.ui_components.models.client.components.vitals.ClientVitals
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ClientHistory(
    val datePosted: Long = 0L,
    val serviceProvider: ServiceProvider? = null,
    val clientInfo: ClientInfo = ClientInfo(),
    val vitals: ClientVitals = ClientVitals(),
    val emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(),
    val notes: List<ClientNote> = emptyList(),
    @Transient val labResults: List<LabResult> = emptyList(),
    @Transient val bookedAppointment: BookedAppointment? = null /* This is for local usage */
) {
    companion object {
        fun mapToLocal(form: ClientHistory) = LocalClientHistory().apply {
            datePosted = form.datePosted
            serviceProvider = form.serviceProvider?.let { ServiceProvider.mapToLocal(it) }
            clientInfo = ClientInfo.mapToLocal(form.clientInfo)
            vitals = ClientVitals.mapToLocal(form.vitals)
            emergencyContactInfo = EmergencyContactInfo.mapToLocal(form.emergencyContactInfo)
            notes = form.notes.map { ClientNote.mapToLocal(it) }.toRealmList()
            labResults = form.labResults.map { LabResult.mapToLocal(it) }.toRealmList()
            bookedAppointment = form.bookedAppointment?.let { BookedAppointment.mapToLocal(it) }
        }

        fun mapToClientItem(form: ClientHistory) = ClientItem(
            serviceProvider = form.serviceProvider,
            clientInfo = form.clientInfo,
            vitals = form.vitals,
            emergencyContactInfo = form.emergencyContactInfo,
            tempNotes = form.notes,
            labResults = form.labResults,
            bookedAppointment = form.bookedAppointment ?: BookedAppointment()
        )
    }
}
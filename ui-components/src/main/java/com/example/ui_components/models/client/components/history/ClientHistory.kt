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
    val bookedAppointmentsDocPaths: List<String> = emptyList(),
    @Transient val bookedAppointments: List<BookedAppointment> = emptyList() /* This is for local usage */
) {
    object Config {
        fun mapToLocal(form: ClientHistory) = LocalClientHistory().apply {
            datePosted = form.datePosted
            serviceProvider = form.serviceProvider?.let { ServiceProvider.Config.mapToLocal(it) }
            clientInfo = ClientInfo.Config.mapToLocal(form.clientInfo)
            vitals = ClientVitals.Config.mapToLocal(form.vitals)
            emergencyContactInfo = EmergencyContactInfo.Config.mapToLocal(form.emergencyContactInfo)
            notes = form.notes.map { ClientNote.Config.mapToLocal(it) }.toRealmList()
            labResults = form.labResults.map { LabResult.Config.mapToLocal(it) }.toRealmList()
            bookedAppointments = form.bookedAppointments.map { BookedAppointment.Config.mapToLocal(it) }.toRealmList()
        }

        fun mapToClientItem(form: ClientHistory) = ClientItem(
            serviceProvider = form.serviceProvider,
            clientInfo = form.clientInfo,
            vitals = form.vitals,
            emergencyContactInfo = form.emergencyContactInfo,
            tempNotes = form.notes,
            labResults = form.labResults,
        )
    }
}
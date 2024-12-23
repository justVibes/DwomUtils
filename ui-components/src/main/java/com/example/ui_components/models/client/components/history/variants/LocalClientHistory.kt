package com.example.ui_components.models.client.components.history.variants

import com.example.ui_components.models.client.components.emergency_contact_info.variants.LocalEmergencyContactInfo
import com.example.ui_components.models.client.components.history.ClientHistory
import com.example.ui_components.models.client.components.info.variants.LocalClientInfo
import com.example.ui_components.models.client.components.lab_result.variants.LocalLabResult
import com.example.ui_components.models.client.components.note.variants.LocalClientNote
import com.example.ui_components.models.client.components.service_provider.LocalServiceProvider
import com.example.ui_components.models.client.components.vitals.variants.LocalClientVitals
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientHistory : EmbeddedRealmObject {
    var datePosted: Long = 0L
    var serviceProvider: LocalServiceProvider? = null
    var clientInfo: LocalClientInfo? = null
    var vitals: LocalClientVitals? = null
    var emergencyContactInfo: LocalEmergencyContactInfo? = null
    var notes: RealmList<LocalClientNote> = realmListOf()
    var labResults: RealmList<LocalLabResult> = realmListOf()
    var bookedAppointment: LocalBookedAppointment? = null

    companion object {
        fun mapToOriginal(form: LocalClientHistory) = ClientHistory(
            datePosted = form.datePosted,
            serviceProvider = LocalServiceProvider.mapToOriginal(
                form.serviceProvider ?: LocalServiceProvider()
            ),
            clientInfo = LocalClientInfo.mapToOriginal(form.clientInfo ?: LocalClientInfo()),
            vitals = LocalClientVitals.mapToOriginal(form.vitals ?: LocalClientVitals()),
            emergencyContactInfo = LocalEmergencyContactInfo.mapToOriginal(
                form.emergencyContactInfo ?: LocalEmergencyContactInfo()
            ),
            notes = form.notes.map { LocalClientNote.mapToOriginal(it) },
            labResults = form.labResults.map { LocalLabResult.mapToOriginal(it) },
            bookedAppointment = form.bookedAppointment?.let {
                LocalBookedAppointment.mapToOriginal(it)
            }
        )

    }
}
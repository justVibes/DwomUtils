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
    var bookedAppointmentsDocPaths: RealmList<String> = realmListOf()
    var bookedAppointments: RealmList<LocalBookedAppointment> = realmListOf()

    object Config {
        fun mapToOriginal(form: LocalClientHistory) = ClientHistory(
            datePosted = form.datePosted,
            serviceProvider = LocalServiceProvider.Config.mapToOriginal(
                form.serviceProvider ?: LocalServiceProvider()
            ),
            clientInfo = LocalClientInfo.Config.mapToOriginal(form.clientInfo ?: LocalClientInfo()),
            vitals = LocalClientVitals.Config.mapToOriginal(form.vitals ?: LocalClientVitals()),
            emergencyContactInfo = LocalEmergencyContactInfo.Config.mapToOriginal(
                form.emergencyContactInfo ?: LocalEmergencyContactInfo()
            ),
            notes = form.notes.map { LocalClientNote.Config.mapToOriginal(it) },
            labResults = form.labResults.map { LocalLabResult.Config.mapToOriginal(it) },
            bookedAppointmentsDocPaths = form.bookedAppointmentsDocPaths.map { it.trim() },
            bookedAppointments = form.bookedAppointments.map {
                LocalBookedAppointment.Config.mapToOriginal(it)
            }
        )

    }
}
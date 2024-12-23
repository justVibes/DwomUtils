package com.example.ui_components.models.client.variants

import com.example.ui_components.models.client.ClientItem
import com.example.ui_components.models.client.components.color.variants.LocalClientColor
import com.example.ui_components.models.client.components.emergency_contact_info.EmergencyContactInfo
import com.example.ui_components.models.client.components.emergency_contact_info.variants.LocalEmergencyContactInfo
import com.example.ui_components.models.client.components.history.variants.LocalClientHistory
import com.example.ui_components.models.client.components.info.ClientInfo
import com.example.ui_components.models.client.components.info.variants.LocalClientInfo
import com.example.ui_components.models.client.components.lab_result.variants.LocalLabResult
import com.example.ui_components.models.client.components.note.variants.LocalClientNote
import com.example.ui_components.models.client.components.service_provider.LocalServiceProvider
import com.example.ui_components.models.client.components.vitals.ClientVitals
import com.example.ui_components.models.client.components.vitals.variants.LocalClientVitals
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class LocalClientItem : RealmObject {
    @PrimaryKey
    var clientId: String = ""
    var serviceProvider: LocalServiceProvider? = null
    var accessorEmails: RealmList<String> = realmListOf()
    var clientInfo: LocalClientInfo? = null
    var vitals: LocalClientVitals? = null
    var emergencyContactInfo: LocalEmergencyContactInfo? = null
    var notes: RealmList<LocalClientNote> = realmListOf()
    var labResults: RealmList<LocalLabResult> = realmListOf()
    var history: RealmList<LocalClientHistory> = realmListOf()
    var bookedAppointment: LocalBookedAppointment? = null
    var clientColor: LocalClientColor? = null


    /* Use this to differentiate between client files that the user owns and downloaded client files */
    @Exclude
    var isDownloaded: Boolean = false

    companion object {
        fun mapToOriginal(form: LocalClientItem) = ClientItem(
            clientId = form.clientId,
            serviceProvider = form.serviceProvider?.let {
                LocalServiceProvider.mapToOriginal(it)
            },
            accessorEmails = form.accessorEmails,
            clientInfo = form.clientInfo?.let { LocalClientInfo.mapToOriginal(it) }
                ?: ClientInfo(),
            vitals = form.vitals?.let { LocalClientVitals.mapToOriginal(it) }
                ?: ClientVitals(),
            emergencyContactInfo = form.emergencyContactInfo?.let {
                LocalEmergencyContactInfo.mapToOriginal(it)
            } ?: EmergencyContactInfo(),
            tempNotes = form.notes.map { LocalClientNote.mapToOriginal(it) },
            labResults = form.labResults.map { LocalLabResult.mapToOriginal(it) },
            history = form.history.map { LocalClientHistory.mapToOriginal(it) },
            bookedAppointment = form.bookedAppointment?.let {
                LocalBookedAppointment.mapToOriginal(it)
            },
            clientColor = LocalClientColor.mapToOriginal(form.clientColor ?: LocalClientColor())
        )

        fun trimmedFields(form: LocalClientItem) = LocalClientItem().apply {
            serviceProvider = LocalServiceProvider.trimmedFields(
                form.serviceProvider ?: LocalServiceProvider()
            )
            accessorEmails = form.accessorEmails.map { it.trim() }.toRealmList()
            clientInfo = LocalClientInfo.trimmedFields(form.clientInfo ?: LocalClientInfo())
            vitals = LocalClientVitals.trimmedFields(form.vitals ?: LocalClientVitals())
            emergencyContactInfo = LocalEmergencyContactInfo.trimmedFields(
                form.emergencyContactInfo ?: LocalEmergencyContactInfo()
            )
            notes = form.notes.map { LocalClientNote.trimmedFields(it) }.toRealmList()
            labResults = form.labResults
            bookedAppointment = form.bookedAppointment?.let {
                LocalBookedAppointment.trimmedFields(it)
            }
        }
    }
}
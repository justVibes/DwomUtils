package com.example.ui_components.models.client.variants

import com.example.ui_components.models.client.ClientItem
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
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.realmListOf
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
    var history: RealmList<LocalClientHistory>? = realmListOf()

    /* Use this to differentiate between client files that the user owns and downloaded client files */
    @Exclude var isDownloaded: Boolean = false

    object Config {
        fun mapToOriginal(form: LocalClientItem) = ClientItem(
            clientId = form.clientId,
            serviceProvider = form.serviceProvider?.let {
                LocalServiceProvider.Config.mapToOriginal(it)
            },
            accessorEmails = form.accessorEmails,
            clientInfo = form.clientInfo?.let { LocalClientInfo.Config.mapToOriginal(it) }
                ?: ClientInfo(),
            vitals = form.vitals?.let { LocalClientVitals.Config.mapToOriginal(it) }
                ?: ClientVitals(),
            emergencyContactInfo = form.emergencyContactInfo?.let {
                LocalEmergencyContactInfo.Config.mapToOriginal(it)
            } ?: EmergencyContactInfo(),
            tempNotes = form.notes.map { LocalClientNote.Config.mapToOriginal(it) },
            labResults = form.labResults.map { LocalLabResult.Config.mapToOriginal(it) },
            history = form.history?.map { LocalClientHistory.Config.mapToOriginal(it) } ?: emptyList()
        )
    }
}
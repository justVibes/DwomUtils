package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.emergency_contact_info.variants.LocalEmergencyContactInfo
import com.example.ui_components.models.client.components.info.variants.LocalClientInfo
import com.example.ui_components.models.client.components.lab_result.variants.LocalLabResult
import com.example.ui_components.models.client.components.note.variants.LocalClientNote
import com.example.ui_components.models.client.components.service_provider.LocalServiceProvider
import com.example.ui_components.models.client.components.vitals.variants.LocalClientVitals
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
    var notes: List<LocalClientNote> = realmListOf()
    var labResults: List<LocalLabResult> = realmListOf()

    object Config {
        fun mapToOriginal(form: LocalClientItem) = ClientItem(
            clientId = form.clientId,
            serviceProvider = form.serviceProvider?.let {
                LocalServiceProvider.Config.mapToOriginal(it)
            },
            accessorEmails = form.accessorEmails,
            clientInfo = form.clientInfo?.let { LocalClientInfo.Config.mapToOriginal(it) },
            vitals = form.vitals?.let { LocalClientVitals.Config.mapToOriginal(it) },
            emergencyContactInfo = form.emergencyContactInfo?.let {
                LocalEmergencyContactInfo.Config.mapToOriginal(it)
            },
            tempNotes = form.notes.map { LocalClientNote.Config.mapToOriginal(it) },
            labResults = form.labResults.map { LocalLabResult.Config.mapToOriginal(it) }
        )
    }
}
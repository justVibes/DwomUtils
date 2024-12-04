package com.example.ui_components.models.client.components.history.variants

import com.example.ui_components.models.client.components.emergency_contact_info.variants.LocalEmergencyContactInfo
import com.example.ui_components.models.client.components.history.ClientHistory
import com.example.ui_components.models.client.components.info.variants.LocalClientInfo
import com.example.ui_components.models.client.components.service_provider.LocalServiceProvider
import com.example.ui_components.models.client.components.vitals.variants.LocalClientVitals
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientHistory : EmbeddedRealmObject {
    var datePosted: Long = 0L
    var serviceProvider: LocalServiceProvider? = null
    var clientInfo: LocalClientInfo? = null
    var vitals: LocalClientVitals? = null
    var emergencyContactInfo: LocalEmergencyContactInfo? = null

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
            )
        )
    }
}
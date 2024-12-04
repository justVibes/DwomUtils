package com.example.ui_components.models.client.components.history

import com.example.ui_components.models.client.components.emergency_contact_info.EmergencyContactInfo
import com.example.ui_components.models.client.components.history.variants.LocalClientHistory
import com.example.ui_components.models.client.components.info.ClientInfo
import com.example.ui_components.models.client.components.service_provider.ServiceProvider
import com.example.ui_components.models.client.components.vitals.ClientVitals
import kotlinx.serialization.Serializable

@Serializable
data class ClientHistory(
    val datePosted: Long = 0L,
    val serviceProvider: ServiceProvider? = null,
    val clientInfo: ClientInfo = ClientInfo(),
    val vitals: ClientVitals = ClientVitals(),
    val emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(),
) {
    object Config {
        fun mapToLocal(form: ClientHistory) = LocalClientHistory().apply {
            datePosted = form.datePosted
            serviceProvider = form.serviceProvider?.let { ServiceProvider.Config.mapToLocal(it) }
            clientInfo = ClientInfo.Config.mapToLocal(form.clientInfo)
            vitals = ClientVitals.Config.mapToLocal(form.vitals)
            emergencyContactInfo = EmergencyContactInfo.Config.mapToLocal(form.emergencyContactInfo)
        }
    }
}
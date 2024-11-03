package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.ClientInfo
import com.example.ui_components.models.client.components.ClientNote
import com.example.ui_components.models.client.components.ClientRecord
import com.example.ui_components.models.client.components.ClientVitals
import com.example.ui_components.models.client.components.EmergencyContactInfo
import com.example.ui_components.models.core.user.ConnectionKey
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class ClientItem(
    val key: ConnectionKey = ConnectionKey(),
    var clientId: String = "${UUID.randomUUID()}",
    var serviceProviderUid: String = "",
    var vitals: ClientVitals = ClientVitals(),
    var clientInfo: ClientInfo = ClientInfo(),
    var emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(),
    var tempNotes: List<ClientNote> = emptyList(),
    var notes: List<String> = emptyList(),
    var labResults: List<String> = emptyList(),
    var history: List<ClientRecord> = emptyList()
)


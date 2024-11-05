package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.ClientInfo
import com.example.ui_components.models.client.components.ClientNote
import com.example.ui_components.models.client.components.ClientRecord
import com.example.ui_components.models.client.components.ClientVitals
import com.example.ui_components.models.client.components.EmergencyContactInfo
import com.example.ui_components.models.core.user.components.ConnectionKey
import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID


@Serializable
data class ClientItem(
    @Transient val connectionKey: ConnectionKey = ConnectionKey(),
    var clientId: String = "${UUID.randomUUID()}",
    var doctorUid: String = "",
    var vitals: ClientVitals = ClientVitals(clientId = clientId),
    var clientInfo: ClientInfo = ClientInfo(clientId = clientId),
    var emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(clientId = clientId),
    var tempNotes: List<ClientNote> = emptyList(), /*This is for local usage.*/
    @Transient var notes: List<DocumentReference> = emptyList(),
    var labResults: List<String> = emptyList(),
    var history: List<ClientRecord> = emptyList()
)


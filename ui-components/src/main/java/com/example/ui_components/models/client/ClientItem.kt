package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.ClientInfo
import com.example.ui_components.models.client.components.ClientNote
import com.example.ui_components.models.client.components.ClientRecord
import com.example.ui_components.models.client.components.ClientVitals
import com.example.ui_components.models.client.components.EmergencyContactInfo
import com.example.ui_components.models.client.components.HighlightedClientInfo
import com.example.ui_components.models.client.components.HighlightedClientVitals
import com.example.ui_components.models.client.components.HighlightedEmergencyContactInfo
import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID


@Serializable
data class ClientItem(
    var clientId: String = "${UUID.randomUUID()}",
    var serviceProviderUid: String = "",
    var serviceProviderName: String = "",
    var authorizedEditors: List<String> = emptyList(),
    var clientInfo: ClientInfo = ClientInfo(),
    var vitals: ClientVitals = ClientVitals(),
    var emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(),
    var clientCopyOwnerEmail: String = "",
    @Transient var originalDocRef: DocumentReference? = null,
    @Transient var clientCopyDocRefs: List<DocumentReference> = emptyList(),
    @Transient var tempClientCopies: List<ClientItemCopy> = emptyList(), /*This is for local usage.*/
    var tempNotes: List<ClientNote> = emptyList(), /*This is for local usage.*/
    @Transient var noteRefs: List<DocumentReference> = emptyList(),
    var labResults: List<String> = emptyList(),
    var history: List<ClientRecord> = emptyList()
) {
    object MapToHighlighted {
        fun from(original: ClientItem, modified: ClientItem): HighlightedClientItem {
            return HighlightedClientItem(
                clientInfo = ClientInfo.MapToHighlighted.from(
                    original.clientInfo,
                    modified.clientInfo
                ),
                vitals = ClientVitals.MapToHighlighted.from(original.vitals, modified.vitals),
                emergencyContactInfo = EmergencyContactInfo.MapToHighlighted.from(
                    original.emergencyContactInfo,
                    modified.emergencyContactInfo
                )
            )
        }
    }
}


data class HighlightedClientItem(
    val clientInfo: HighlightedClientInfo = HighlightedClientInfo(),
    val vitals: HighlightedClientVitals = HighlightedClientVitals(),
    val emergencyContactInfo: HighlightedEmergencyContactInfo = HighlightedEmergencyContactInfo(),
)






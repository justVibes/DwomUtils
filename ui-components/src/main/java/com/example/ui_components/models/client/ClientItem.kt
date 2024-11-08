package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.ClientCopyOwner
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
    @Transient var clientCopyOwner: ClientCopyOwner? = null,
    @Transient var originalDocRef: DocumentReference? = null,
    @Transient var clientCopyDocRefs: List<DocumentReference> = emptyList(),
    @Transient var tempClientCopies: List<ClientItemCopy> = emptyList(), /*This is for local usage.*/
    var tempNotes: List<ClientNote> = emptyList(), /*This is for local usage.*/
    @Transient var noteRefs: List<DocumentReference> = emptyList(),
    var labResults: List<String> = emptyList(),
    var history: List<ClientRecord> = emptyList()
) {
    object Config {
        fun mapToHighlighted(original: ClientItem, modified: ClientItem): HighlightedClientItem {
            return HighlightedClientItem(
                clientInfo = ClientInfo.Config.mapToHighlighted(
                    original.clientInfo,
                    modified.clientInfo
                ),
                vitals = ClientVitals.Config.mapToHighlighted(original.vitals, modified.vitals),
                emergencyContactInfo = EmergencyContactInfo.Config.mapToHighlighted(
                    original.emergencyContactInfo,
                    modified.emergencyContactInfo
                )
            )
        }

        fun trimmedFields(form: ClientItem) =
            form.copy(
                clientInfo = ClientInfo.Config.trimmedFields(form.clientInfo),
                vitals = ClientVitals.Config.trimmedFields(form.vitals),
                emergencyContactInfo = EmergencyContactInfo.Config.trimmedFields(form.emergencyContactInfo)
            )
    }
}


data class HighlightedClientItem(
    val clientInfo: HighlightedClientInfo = HighlightedClientInfo(),
    val vitals: HighlightedClientVitals = HighlightedClientVitals(),
    val emergencyContactInfo: HighlightedEmergencyContactInfo = HighlightedEmergencyContactInfo(),
)






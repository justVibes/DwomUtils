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
    var clientInfo: ClientInfo = ClientInfo(),
    var vitals: ClientVitals = ClientVitals(),
    var emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(),

    /*
    * References the notes created for this client, which is stored in a sub collection
    * of the client's document
    */
    @Transient var noteRefs: List<DocumentReference> = emptyList(),

    /* If this version of the client is a copy then 'clientCopyOwner' is initialized */
    @Transient var clientCopyOwner: ClientCopyOwner? = null,

    /* This is the location of the current client's info */
    @Transient var originalDocRef: DocumentReference? = null,

    /*
    * This list can only contain references to workers that are apart of the same
    * establishment as the service provider who created the client.
    */
    @Transient var authorizedEditorRefs: List<DocumentReference> = emptyList(),

    /*
    * This is for local usage.
    * It's used to visualize the authorized editors for the service provider who created the client (the owner),
    * so that they (the owner) can add or remove them (the editors).
    */
    @Transient var tempAuthorizedEditors: List<ClientCopyOwner> = emptyList(),

    /*
    * This is for local usage.
    * It's for editing and viewing notes.
    */
    var tempNotes: List<ClientNote> = emptyList(),

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






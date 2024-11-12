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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.apache.poi.xwpf.usermodel.XWPFDocument
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
    @Transient var authorizedCopyOwnerRefs: List<DocumentReference> = emptyList(),

    /*
    * This is for local usage.
    * It's used to visualize the authorized editors for the service provider who created the client (the owner),
    * so that they (the owner) can add or remove them (the editors).
    */
    @Transient var tempAuthorizedCopyOwners: List<ClientCopyOwner> = emptyList(),

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
                emergencyContactInfo = EmergencyContactInfo.Config.trimmedFields(form.emergencyContactInfo),
                tempNotes = form.tempNotes.map { ClientNote.Config.trimmedFields(it) }
            )

        fun mapToString(form: ClientItem): String {
            val formattedForm = trimmedFields(form)
            return """
                Client Info.
                ${ClientInfo.Config.mapToString(form.clientInfo)}
                
                Emergency Contact Info.
                ${EmergencyContactInfo.Config.mapToString(form.emergencyContactInfo)}
                
                Vitals
                ${ClientVitals.Config.mapToString(form.vitals)}
                
                Notes
                ${form.tempNotes.joinToString("\n\n") { ClientNote.Config.mapToString(it) }}
            """.trimIndent()
        }


        suspend fun mapToXWPFDoc(form: ClientItem): XWPFDocument {
            val wordDocument = XWPFDocument()
            val paragraph = wordDocument.createParagraph()
            val run = paragraph.createRun()
            val sections = listOf(
                "Client Info." to ClientInfo.Config.mapToListOfPairs(form.clientInfo),
                "Emergency Contact Info." to EmergencyContactInfo.Config.mapToListOfPairs(form.emergencyContactInfo),
                "Vitals" to ClientVitals.Config.mapToListOfPairs(form.vitals),
            )
            run.setText("Client created by ${form.serviceProviderName}")
            run.addBreak()
            run.addBreak()

            withContext(Dispatchers.Default) {
                async {
                    sections.forEach { section ->
                        run.setText(section.first)
                        run.addBreak()
                        section.second.forEach { sectionContent ->
                            run.setText("${sectionContent.first}: ${sectionContent.second}")
                            run.addBreak()
                        }
                        run.addBreak()
                    }
                    run.setText("Notes")
                    form.tempNotes.forEach { note ->
                        ClientNote.Config.mapToListOfPairs(note).forEach { noteSection ->
                            run.setText("${noteSection.first}: ${noteSection.second}")
                            run.addBreak()
                        }
                        run.addBreak()
                    }
                }
            }.await()

            return wordDocument
        }
    }
}


data class HighlightedClientItem(
    val clientInfo: HighlightedClientInfo = HighlightedClientInfo(),
    val vitals: HighlightedClientVitals = HighlightedClientVitals(),
    val emergencyContactInfo: HighlightedEmergencyContactInfo = HighlightedEmergencyContactInfo(),
)






package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.ClientCopyOwner
import com.example.ui_components.models.client.components.core.EditType
import com.example.ui_components.models.client.components.emergency_contact_info.EmergencyContactInfo
import com.example.ui_components.models.client.components.info.ClientInfo
import com.example.ui_components.models.client.components.lab_result.LabResult
import com.example.ui_components.models.client.components.note.ClientNote
import com.example.ui_components.models.client.components.note.variants.HighlightedClientNote
import com.example.ui_components.models.client.components.record.ClientRecord
import com.example.ui_components.models.client.components.service_provider.ServiceProvider
import com.example.ui_components.models.client.components.vitals.ClientVitals
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.toRealmList
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
    var serviceProvider: ServiceProvider? = null,
    var accessorEmails: List<String> = emptyList(),
    var clientInfo: ClientInfo = ClientInfo(),
    var vitals: ClientVitals = ClientVitals(),
    var emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(),

    /*
    * References the notes created for this client, which is stored in a sub collection
    * of the client's document
    */
    @Transient var noteRefs: List<DocumentReference> = emptyList(),

    /* This is the location of the current client's info */
    @Transient var originalDocRef: DocumentReference? = null,

    /*
    * This is for local usage.
    * It's for editing and viewing notes.
    */
    @Exclude var tempNotes: List<ClientNote> = emptyList(),
    @Transient var labResults: List<LabResult> = emptyList(),
    @Exclude var history: List<ClientRecord> = emptyList() /* This is yet to be added */
) {
    object Config {
        fun mapToLocal(form: ClientItem) = LocalClientItem().apply {
            clientId = form.clientId
            serviceProvider = form.serviceProvider?.let {
                ServiceProvider.Config.mapToLocal(it)
            }
            accessorEmails = form.accessorEmails.toRealmList()
            clientInfo = form.clientInfo.let {
                ClientInfo.Config.mapToLocal(it)
            }
            vitals = form.vitals.let {
                ClientVitals.Config.mapToLocal(it)
            }
            emergencyContactInfo = form.emergencyContactInfo.let {
                EmergencyContactInfo.Config.mapToLocal(it)
            }
            notes = form.tempNotes.map { ClientNote.Config.mapToLocal(it) }
            labResults = form.labResults.map { LabResult.Config.mapToLocal(it) }
        }

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
                ),
                notes = modified.tempNotes.mapIndexed { index, note ->
                    try {
                        ClientNote.Config.mapToHighlighted(note, original.tempNotes[index])
                    } catch (e: IndexOutOfBoundsException) {
                        HighlightedClientNote(isNew = EditType.ADDED)
                    }
                }
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
                ${formattedForm.clientInfo.let { ClientInfo.Config.mapToString(it) }}
                
                Emergency Contact Info.
                ${
                formattedForm.emergencyContactInfo.let {
                    EmergencyContactInfo.Config.mapToString(it)
                } 
            }
                
                Vitals
                ${formattedForm.vitals.let { ClientVitals.Config.mapToString(it) }}
                
                Notes
                ${form.tempNotes.joinToString("\n\n") { ClientNote.Config.mapToString(it) }}
            """.trimIndent()
        }


        suspend fun mapToXWPFDoc(form: ClientItem): XWPFDocument {
            val wordDocument = XWPFDocument()
            val paragraph = wordDocument.createParagraph()
            val run = paragraph.createRun()
            val sections = listOf(
                "Client Info." to form.clientInfo.let { ClientInfo.Config.mapToListOfPairs(it) },
                "Emergency Contact Info." to form.emergencyContactInfo.let {
                    EmergencyContactInfo.Config.mapToListOfPairs(it)
                },
                "Vitals" to form.vitals.let { ClientVitals.Config.mapToListOfPairs(it) },
            )
            run.setText("Client created by ${form.serviceProvider!!.name}")
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








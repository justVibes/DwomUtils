package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.color.CustomColor
import com.example.ui_components.models.client.components.core.EditType
import com.example.ui_components.models.client.components.emergency_contact_info.EmergencyContactInfo
import com.example.ui_components.models.client.components.history.ClientHistory
import com.example.ui_components.models.client.components.info.ClientInfo
import com.example.ui_components.models.client.components.lab_result.LabResult
import com.example.ui_components.models.client.components.note.ClientNote
import com.example.ui_components.models.client.components.note.variants.HighlightedClientNote
import com.example.ui_components.models.client.components.service_provider.ServiceProvider
import com.example.ui_components.models.client.components.vitals.ClientVitals
import com.example.ui_components.models.client.variants.HighlightedClientItem
import com.example.ui_components.models.client.variants.LocalClientItem
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.toRealmList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.util.Calendar
import java.util.UUID

@Serializable
data class ClientItem(
    val clientId: String = "${UUID.randomUUID()}",
    val creationDate: Long = 0L,
    val seenByAssistant: Boolean = false,
    val serviceProvider: ServiceProvider? = null,
    val accessorEmails: List<String> = emptyList(),
    val clientInfo: ClientInfo = ClientInfo(),
    val vitals: ClientVitals = ClientVitals(),
    val emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(),
    val history: List<ClientHistory> = emptyList(),
    @Transient val bookedAppointment: BookedAppointment = BookedAppointment(),
    @Transient val customColor: CustomColor = CustomColor(rand1, rand2, rand3),
    /*
    * References the notes created for this client, which is stored in a sub collection
    * of the client's document
    */
    @Transient val noteRefs: List<DocumentReference> = emptyList(),
    @Transient val historyRefs: List<DocumentReference> = emptyList(),

    /* This is the location of the current client's info */
    @Transient val originalDocRef: DocumentReference? = null,

    /*
    * This is for local usage.
    * It's for editing and viewing notes.
    */
    @Exclude val tempNotes: List<ClientNote> = emptyList(),
    @Transient val labResults: List<LabResult> = emptyList()
) {
    companion object {
        internal val rand1 = (20..235).random()
        internal val rand2 = (20..235).random()
        internal val rand3 = (20..235).random()

        fun mapToLocal(form: ClientItem) = LocalClientItem().apply {
            clientId = form.clientId
            seenByAssistant = form.seenByAssistant
            serviceProvider = form.serviceProvider?.let {
                ServiceProvider.mapToLocal(it)
            }
            accessorEmails = form.accessorEmails.toRealmList()
            clientInfo = form.clientInfo.let {
                ClientInfo.mapToLocal(it)
            }
            vitals = form.vitals.let {
                ClientVitals.mapToLocal(it)
            }
            emergencyContactInfo = form.emergencyContactInfo.let {
                EmergencyContactInfo.mapToLocal(it)
            }
            notes = form.tempNotes.map { ClientNote.mapToLocal(it) }.toRealmList()
            labResults = form.labResults.map { LabResult.mapToLocal(it) }.toRealmList()
            history = form.history.map { ClientHistory.mapToLocal(it) }.toRealmList()
            bookedAppointment = BookedAppointment.mapToLocal(form.bookedAppointment)
            clientColor = CustomColor.mapToLocal(form.customColor)
        }

        fun mapToHistory(form: ClientItem): ClientHistory {
            val formattedForm = trimmedFields(form)
            return ClientHistory(
                serviceProvider = formattedForm.serviceProvider,
                clientInfo = formattedForm.clientInfo,
                vitals = formattedForm.vitals,
                emergencyContactInfo = formattedForm.emergencyContactInfo,
                datePosted = Calendar.getInstance().timeInMillis,
                notes = formattedForm.tempNotes,
                labResults = formattedForm.labResults
            )
        }

        fun mapToHighlighted(original: ClientItem, modified: ClientItem): HighlightedClientItem {
            return HighlightedClientItem(
                clientInfo = ClientInfo.mapToHighlighted(
                    original.clientInfo,
                    modified.clientInfo
                ),
                vitals = ClientVitals.mapToHighlighted(original.vitals, modified.vitals),
                emergencyContactInfo = EmergencyContactInfo.mapToHighlighted(
                    original.emergencyContactInfo,
                    modified.emergencyContactInfo
                ),
                notes = modified.tempNotes.mapIndexed { index, note ->
                    try {
                        ClientNote.mapToHighlighted(note, original.tempNotes[index])
                    } catch (e: IndexOutOfBoundsException) {
                        HighlightedClientNote(isNew = EditType.ADDED)
                    }
                }
            )
        }

        fun trimmedFields(form: ClientItem) =
            form.copy(
                clientInfo = ClientInfo.trimmedFields(form.clientInfo),
                vitals = ClientVitals.trimmedFields(form.vitals),
                emergencyContactInfo = EmergencyContactInfo.trimmedFields(form.emergencyContactInfo),
                tempNotes = form.tempNotes.map { ClientNote.trimmedFields(it) }
            )

        fun mapToString(form: ClientItem): String {
            val formattedForm = trimmedFields(form)
            return """
                Client Info.
                ${formattedForm.clientInfo.let { ClientInfo.mapToString(it) }}
                
                Emergency Contact Info.
                ${
                formattedForm.emergencyContactInfo.let {
                    EmergencyContactInfo.mapToString(it)
                }
            }
                
                Vitals
                ${formattedForm.vitals.let { ClientVitals.mapToString(it) }}
                
                Notes
                ${form.tempNotes.joinToString("\n\n") { ClientNote.mapToString(it) }}
            """.trimIndent()
        }


        suspend fun mapToXWPFDoc(form: ClientItem): XWPFDocument {
            val wordDocument = XWPFDocument()
            val paragraph = wordDocument.createParagraph()
            val run = paragraph.createRun()
            val sections = listOf(
                "Client Info." to form.clientInfo.let { ClientInfo.mapToListOfPairs(it) },
                "Emergency Contact Info." to form.emergencyContactInfo.let {
                    EmergencyContactInfo.mapToListOfPairs(it)
                },
                "Vitals" to form.vitals.let { ClientVitals.mapToListOfPairs(it) },
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
                        ClientNote.mapToListOfPairs(note).forEach { noteSection ->
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








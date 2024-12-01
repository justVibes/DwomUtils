package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.core.EditType
import com.example.ui_components.models.client.components.core.stringComparison
import kotlinx.serialization.Serializable
import java.util.Calendar
import java.util.UUID

@Serializable
data class ClientNote(
    val noteId: String = "${UUID.randomUUID()}",
    val creationDate: Long = Calendar.getInstance().timeInMillis,
    var clientId: String = "",
    var authorEmail: String = "",
    var title: String = "",
    var note: String = "",
    val accessEmails: List<String> = emptyList(),
) {
    object Config {
        fun mapToStripped(form: ClientNote) = ClientNoteStripped(
            noteId = form.noteId,
            creationDate = form.creationDate,
            clientId = form.clientId,
            author = form.authorEmail,
            title = form.title,
            note = form.note,
        )

        fun mapToHighlighted(original: ClientNote, modified: ClientNote): HighlightedClientNote {
            val formattedForm: (ClientNote) -> ClientNote = { note ->
                trimmedFields(note)
            }
            return HighlightedClientNote(
                title = stringComparison(
                    formattedForm(original).title,
                    formattedForm(modified).title
                ),
                note = stringComparison(formattedForm(original).note, formattedForm(modified).note),
            )
        }

        fun trimmedFields(form: ClientNote) =
            form.apply {
                authorEmail = authorEmail.trim()
                title = title.trim()
                note = note.trim()
            }

        fun mapToString(form: ClientNote): String {
            val formattedForm = trimmedFields(form)
            return """
                Author: ${formattedForm.authorEmail.ifEmpty { "n/a" }}
                Title: ${formattedForm.title.ifEmpty { "n/a" }}
                Note: ${formattedForm.note.ifEmpty { "n/a" }}
            """.trimIndent()
        }

        fun mapToListOfPairs(form: ClientNote): List<Pair<String, String>> {
            val formattedForm = trimmedFields(form)
            return listOf(
                Pair("Author", formattedForm.authorEmail.ifEmpty { "n/a" }),
                Pair("Title", formattedForm.title.ifEmpty { "n/a" }),
                Pair("Note", formattedForm.note.ifEmpty { "n/a" }),
            )
        }
    }
}

@Entity
data class ClientNoteStripped(
    @PrimaryKey
    val noteId: String = "${UUID.randomUUID()}",
    val creationDate: Long = Calendar.getInstance().timeInMillis,
    var clientId: String = "",
    var author: String = "",
    var title: String = "",
    var note: String = "",
){
    object Config {
        fun mapToOriginal(form: ClientNoteStripped, accessEmails: List<String>) = ClientNote(
            noteId = form.noteId,
            creationDate = form.creationDate,
            clientId = form.clientId,
            authorEmail = form.author,
            title = form.title,
            note = form.note,
            accessEmails = accessEmails
        )
    }
}

data class HighlightedClientNote(
    var isNew: EditType = EditType.NONE,
    var author: EditType = EditType.NONE,
    var title: EditType = EditType.NONE,
    var note: EditType = EditType.NONE,
)

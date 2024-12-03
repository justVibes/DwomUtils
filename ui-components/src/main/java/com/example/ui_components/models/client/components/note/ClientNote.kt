package com.example.ui_components.models.client.components.note

import com.example.ui_components.models.client.components.core.stringComparison
import com.example.ui_components.models.client.components.note.variants.HighlightedClientNote
import com.example.ui_components.models.client.components.note.variants.LocalClientNote
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable
import java.util.Calendar
import java.util.UUID

@Serializable
data class ClientNote(
    val noteId: String = "${UUID.randomUUID()}",
    val creationDate: Long = Calendar.getInstance().timeInMillis,
    var authorEmail: String = "",
    var title: String = "",
    var note: String = "",
    var accessEmails: List<String> = emptyList(),
) {
    object Config {
        fun mapToLocal(form: ClientNote) = LocalClientNote().apply {
            val formattedForm = trimmedFields(form)
            noteId = formattedForm.noteId
            creationDate = formattedForm.creationDate
            authorEmail = formattedForm.authorEmail
            title = formattedForm.title
            note = formattedForm.note
            accessEmails = form.accessEmails.toRealmList()
        }

        fun mapToHighlighted(original: ClientNote, modified: ClientNote): HighlightedClientNote {
            val formattedOriginal = trimmedFields(original)
            val formattedModified = trimmedFields(modified)

            fun getIndexOfChangedWords(original: String, modified: String): List<Int> {
                val indexOfChangedWordsList = mutableListOf<Int>()
                if (original != modified) {
                    modified.split(" ").forEachIndexed { idx, modWord ->
                        if (original.split(" ")[idx] != modWord) {
                            indexOfChangedWordsList.add(idx)
                        }
                    }
                }
                return indexOfChangedWordsList
            }

            return HighlightedClientNote(
                title = stringComparison(formattedOriginal.title, formattedModified.title),
                note = stringComparison(formattedOriginal.note, formattedModified.note),
                indexOfChangedTitleWordsList = getIndexOfChangedWords(
                    formattedOriginal.title,
                    formattedModified.title
                ),
                indexOfChangedNoteWordsList = getIndexOfChangedWords(
                    formattedOriginal.note,
                    formattedModified.note
                )
            )
        }

        fun trimmedFields(form: ClientNote?) =
            form?.copy(
                authorEmail = form.authorEmail.trim(),
                title = form.title.trim(),
                note = form.note.trim(),
                accessEmails = form.accessEmails.map { it.trim() },
            ) ?: ClientNote()

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



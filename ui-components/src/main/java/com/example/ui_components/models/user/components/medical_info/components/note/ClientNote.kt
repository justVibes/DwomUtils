package com.example.ui_components.models.user.components.medical_info.components.note

import com.example.ui_components.models.user.components.medical_info.components.note.variants.LocalClientNote
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
    companion object {
        fun mapToLocal(form: ClientNote) = LocalClientNote().apply {
            val formattedForm = trimmedFields(form)
            noteId = formattedForm.noteId
            creationDate = formattedForm.creationDate
            authorEmail = formattedForm.authorEmail
            title = formattedForm.title
            note = formattedForm.note
            accessEmails = form.accessEmails.toRealmList()
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



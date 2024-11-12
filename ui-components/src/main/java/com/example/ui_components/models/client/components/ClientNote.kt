package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Calendar
import java.util.UUID

@Serializable
@Entity
data class ClientNote(
    @PrimaryKey
    val noteId: String = "${UUID.randomUUID()}",
    val creationDate: Long = Calendar.getInstance().timeInMillis,
    var clientId: String = "",
    var author: String = "",
    var title: String = "",
    var note: String = "",
) {
    object Config {
        fun trimmedFields(form: ClientNote) =
            form.apply {
                author = author.trim()
                title = title.trim()
                note = note.trim()
            }

        fun mapToString(form: ClientNote): String {
            val formattedForm = trimmedFields(form)
            return """
                Author: ${form.author.ifEmpty { "n/a" }}
                Title: ${form.title.ifEmpty { "n/a" }}
                Note: ${form.note.ifEmpty { "n/a" }}
            """.trimIndent()
        }

        fun mapToListOfPairs(form: ClientNote): List<Pair<String, String>> {
            val formattedForm = trimmedFields(form)
            return listOf(
                Pair("Author", form.author.ifEmpty { "n/a" }),
                Pair("Title", form.title.ifEmpty { "n/a" }),
                Pair("Note", form.note.ifEmpty { "n/a" }),
            )
        }
    }
}

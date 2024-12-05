package com.example.ui_components.models.client.components.note.variants

import com.example.ui_components.models.client.components.note.ClientNote
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList

class LocalClientNote: EmbeddedRealmObject {
    var noteId: String = ""
    var creationDate: Long = RealmInstant.now().epochSeconds
    var authorEmail: String = ""
    var title: String = ""
    var note: String = ""
    var accessEmails: RealmList<String> = realmListOf()

    object Config {
        fun mapToOriginal(form: LocalClientNote): ClientNote {
            val formattedForm = trimmedFields(form)
            return ClientNote(
                noteId = formattedForm.noteId,
                creationDate = formattedForm.creationDate,
                authorEmail = formattedForm.authorEmail,
                title = formattedForm.title,
                note = formattedForm.note,
                accessEmails = formattedForm.accessEmails.toList()
            )
        }

        fun trimmedFields(form: LocalClientNote) = LocalClientNote().apply {
            noteId = form.noteId.trim()
            authorEmail = form.authorEmail.trim()
            title = form.title.trim()
            note = form.note.trim()
            accessEmails = form.accessEmails.map { it.trim() }.toRealmList()
        }
    }
}
package com.example.ui_components.models.client.components.summary.variants

import com.example.ui_components.models.client.components.core.name.LocalName
import com.example.ui_components.models.client.components.summary.ClientSummary
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientSummary : EmbeddedRealmObject {
    var uid: String = ""
    var photoUrl: String = ""
    var name: LocalName? = null
    var email: String = ""
    var docPath: String? = null

    companion object {
        fun mapToOriginal(form: LocalClientSummary): ClientSummary {
            val formattedForm = trimmedFields(form)
            return ClientSummary(
                uid = formattedForm.uid,
                photoUrl = formattedForm.photoUrl,
                name = LocalName.mapToOriginal(formattedForm.name ?: LocalName()),
                email = formattedForm.email,
                docPath = formattedForm.docPath
            )
        }

        fun trimmedFields(form: LocalClientSummary) = LocalClientSummary().apply {
            uid = form.uid.trim()
            photoUrl = form.photoUrl.trim()
            name = form.name?.let { LocalName.trimmedFields(it) }
            email = form.email.trim()
            docPath = form.docPath?.trim()
        }
    }
}
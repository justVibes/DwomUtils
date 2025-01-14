package com.example.ui_components.models.user.components.core.summary.variants

import com.example.ui_components.models.core.name.variants.LocalName
import com.example.ui_components.models.user.components.core.summary.UserSummary
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalUserSummary : EmbeddedRealmObject {
    var uid: String = ""
    var photoUrl: String = ""
    var name: LocalName? = null
    var email: String = ""
    var docPath: String? = null

    companion object {
        fun mapToOriginal(form: LocalUserSummary): UserSummary {
            val formattedForm = trimmedFields(form)
            return UserSummary(
                uid = formattedForm.uid,
                photoUrl = formattedForm.photoUrl,
                name = LocalName.mapToOriginal(formattedForm.name ?: LocalName()),
                email = formattedForm.email,
                docPath = formattedForm.docPath
            )
        }

        fun trimmedFields(form: LocalUserSummary) = LocalUserSummary().apply {
            uid = form.uid.trim()
            photoUrl = form.photoUrl.trim()
            name = form.name?.let { LocalName.trimmedFields(it) }
            email = form.email.trim()
            docPath = form.docPath?.trim()
        }
    }
}
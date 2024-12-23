package com.example.ui_components.models.client.components.service_provider

import com.example.ui_components.models.client.components.core.name.LocalName
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalServiceProvider : EmbeddedRealmObject {
    var abvTitle: String = ""
    var uid: String = ""
    var name: LocalName? = null
    var email: String = ""
    var photoUrl: String = ""

    companion object  {
        fun mapToOriginal(form: LocalServiceProvider): ServiceProvider {
            val formattedForm = trimmedFields(form)
            return ServiceProvider(
                uid = formattedForm.uid,
                name = LocalName.mapToOriginal(formattedForm.name ?: LocalName()),
                email = formattedForm.email,
                photoUrl = formattedForm.photoUrl,
                abvTitle = formattedForm.abvTitle
            )
        }

        fun trimmedFields(form: LocalServiceProvider) = LocalServiceProvider().apply {
            uid = form.uid.trim()
            name = form.name?.let { LocalName.trimmedFields(it) }
            email = form.email.trim()
            photoUrl = form.photoUrl.trim()
            abvTitle = form.abvTitle.trim()
        }
    }
}
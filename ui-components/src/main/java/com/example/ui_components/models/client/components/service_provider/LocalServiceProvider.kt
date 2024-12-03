package com.example.ui_components.models.client.components.service_provider

import io.realm.kotlin.types.EmbeddedRealmObject

class LocalServiceProvider : EmbeddedRealmObject {
    var uid: String = ""
    var name: String = ""

    object Config {
        fun mapToOriginal(form: LocalServiceProvider): ServiceProvider {
            val formattedForm = trimmedFields(form)
            return ServiceProvider(
                uid = formattedForm.uid,
                name = formattedForm.name
            )
        }

        fun trimmedFields(form: LocalServiceProvider) = form.apply {
            uid = uid.trim()
            name = name.trim()
        }
    }
}
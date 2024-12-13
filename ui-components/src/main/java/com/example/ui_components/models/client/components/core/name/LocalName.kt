package com.example.ui_components.models.client.components.core.name

import io.realm.kotlin.types.EmbeddedRealmObject

class LocalName : EmbeddedRealmObject {
    var first: String = ""
    var last: String = ""

    object Config {
        fun mapToOriginal(form: LocalName): Name {
            val formattedForm = trimmedFields(form)
            return Name(
                first = formattedForm.first,
                last = formattedForm.last
            )
        }


        fun trimmedFields(form: LocalName) = LocalName().apply {
            first = form.first.trim()
            last = form.last
        }
    }
}
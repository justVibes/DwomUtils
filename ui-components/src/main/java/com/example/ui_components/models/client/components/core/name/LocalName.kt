package com.example.ui_components.models.client.components.core.name

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.annotations.Index

class LocalName : EmbeddedRealmObject {
    var first: String = ""
    var last: String = ""
    var middle: String = ""
    @Index
    val lcName: String = first.trim().lowercase() + last.trim().lowercase()

    companion object {
        fun mapToOriginal(form: LocalName): Name {
            val formattedForm = trimmedFields(form)
            return Name(
                first = formattedForm.first,
                last = formattedForm.last,
                middle = formattedForm.middle
            )
        }


        fun trimmedFields(form: LocalName) = LocalName().apply {
            first = form.first.trim()
            last = form.last.trim()
            middle = form.middle
        }
    }
}
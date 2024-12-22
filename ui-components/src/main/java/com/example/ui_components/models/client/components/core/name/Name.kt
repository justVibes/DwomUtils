package com.example.ui_components.models.client.components.core.name

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val first: String = "",
    val last: String = "",
    val middle: String = ""
) {
    object Config {
        fun mapToLocal(form: Name) = LocalName().apply {
            val formattedFields = trimmedFields(form)
            first = formattedFields.first
            last = formattedFields.last
            middle = formattedFields.middle
        }

        fun trimmedFields(form: Name) = form.copy(
            first = form.first.trim(),
            last = form.last.trim(),
            middle = form.middle
        )
    }
}
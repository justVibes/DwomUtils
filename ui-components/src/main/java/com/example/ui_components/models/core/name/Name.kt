package com.example.ui_components.models.core.name

import com.example.ui_components.models.core.name.variants.LocalName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val first: String = "",
    val last: String = "",
    val middle: String = ""
) {
    companion object {
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
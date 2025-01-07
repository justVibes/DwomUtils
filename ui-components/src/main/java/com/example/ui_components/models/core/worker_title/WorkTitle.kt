package com.example.ui_components.models.core.worker_title

import com.example.ui_components.models.core.worker_title.variants.LocalWorkTitle
import kotlinx.serialization.Serializable

@Serializable
data class WorkTitle(
    val fmt: String = "",
    val abv: String = ""
) {
    companion object {
        fun mapToLocal(form: WorkTitle) = LocalWorkTitle().apply {
            val formattedForm = trimmedFields(form)
            fmt = formattedForm.fmt
            abv = formattedForm.abv
        }

        fun trimmedFields(form: WorkTitle) = form.copy(
            fmt = form.fmt.trim(),
            abv = form.abv.trim()
        )
    }
}



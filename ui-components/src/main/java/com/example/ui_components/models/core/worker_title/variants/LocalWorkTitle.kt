package com.example.ui_components.models.core.worker_title.variants

import com.example.ui_components.models.core.worker_title.WorkTitle
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalWorkTitle : EmbeddedRealmObject {
    var fmt: String = ""
    var abv: String = ""

    companion object {
        fun mapToOriginal(form: LocalWorkTitle): WorkTitle {
            val formattedForm = trimmedFields(form)
            return WorkTitle(
                fmt = formattedForm.fmt,
                abv = formattedForm.abv
            )
        }

        fun trimmedFields(form: LocalWorkTitle) = LocalWorkTitle().apply {
            fmt = form.fmt.trim()
            abv = form.abv.trim()
        }
    }
}
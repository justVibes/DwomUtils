package com.example.ui_components.models.core.worker_title.variants

import com.example.ui_components.models.core.worker_title.WorkerTitle
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalWorkerTitle : EmbeddedRealmObject {
    var fmt: String = ""
    var abv: String = ""

    companion object {
        fun mapToOriginal(form: LocalWorkerTitle): WorkerTitle {
            val formattedForm = trimmedFields(form)
            return WorkerTitle(
                fmt = formattedForm.fmt,
                abv = formattedForm.abv
            )
        }

        fun trimmedFields(form: LocalWorkerTitle) = LocalWorkerTitle().apply {
            fmt = form.fmt.trim()
            abv = form.abv.trim()
        }
    }
}
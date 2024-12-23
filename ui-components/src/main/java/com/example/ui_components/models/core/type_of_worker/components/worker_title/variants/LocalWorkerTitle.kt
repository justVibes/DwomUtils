package com.example.ui_components.models.core.type_of_worker.components.worker_title.variants

import com.example.ui_components.models.core.type_of_worker.components.worker_title.WorkerTitle
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalWorkerTitle : EmbeddedRealmObject {
    var formatted: String = ""
    var abv: String = ""

    companion object {
        fun mapToOriginal(form: LocalWorkerTitle): WorkerTitle {
            val formattedForm = trimmedFields(form)
            return WorkerTitle(
                formatted = formattedForm.formatted,
                abv = formattedForm.abv
            )
        }

        fun trimmedFields(form: LocalWorkerTitle) = LocalWorkerTitle().apply {
            formatted = form.formatted.trim()
            abv = form.abv.trim()
        }
    }
}
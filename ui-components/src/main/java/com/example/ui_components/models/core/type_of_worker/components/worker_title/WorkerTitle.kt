package com.example.ui_components.models.core.type_of_worker.components.worker_title

import com.example.ui_components.models.core.type_of_worker.components.worker_title.variants.LocalWorkerTitle

data class WorkerTitle(
    val formatted: String = "",
    val abv: String = ""
) {
    companion object {
        fun mapToLocal(form: WorkerTitle) = LocalWorkerTitle().apply {
            val formattedForm = trimmedFields(form)
            formatted = formattedForm.formatted
            abv = formattedForm.abv
        }

        fun trimmedFields(form: WorkerTitle) = form.copy(
            formatted = form.formatted.trim(),
            abv = form.abv.trim()
        )
    }
}



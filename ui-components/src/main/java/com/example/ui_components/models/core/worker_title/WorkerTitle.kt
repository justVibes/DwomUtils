package com.example.ui_components.models.core.worker_title

import com.example.ui_components.models.core.worker_title.variants.LocalWorkerTitle

data class WorkerTitle(
    val fmt: String = "",
    val abv: String = ""
) {
    companion object {
        fun mapToLocal(form: WorkerTitle) = LocalWorkerTitle().apply {
            val formattedForm = trimmedFields(form)
            fmt = formattedForm.fmt
            abv = formattedForm.abv
        }

        fun trimmedFields(form: WorkerTitle) = form.copy(
            fmt = form.fmt.trim(),
            abv = form.abv.trim()
        )
    }
}



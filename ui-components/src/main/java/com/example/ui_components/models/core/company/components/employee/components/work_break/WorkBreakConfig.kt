package com.example.ui_components.models.core.company.components.employee.components.work_break

import com.example.ui_components.models.core.company.components.employee.components.work_break.variants.LocalWorkBreakConfig

data class WorkBreakConfig(
    val startTime: Long = 0L,
    val endTime: Long = 0L,
    val durationInMins: String = ""
) {
    companion object {
        fun mapToLocal(form: WorkBreakConfig) = LocalWorkBreakConfig().apply {
            startTime = form.startTime
            endTime = form.endTime
            durationInMins = form.durationInMins.filter { it.isDigit() }
        }
    }
}
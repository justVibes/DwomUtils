package com.example.ui_components.models.core.company.components.employee.components.work_break.variants

import com.example.ui_components.models.core.company.components.employee.components.work_break.WorkBreakConfig
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalWorkBreakConfig : EmbeddedRealmObject {
    var startTime: Long = 0L
    var endTime: Long = 0L
    var durationInMins: String = ""

    companion object {
        fun mapToOriginal(form: LocalWorkBreakConfig) = WorkBreakConfig(
            startTime = form.startTime,
            endTime = form.endTime,
            durationInMins = form.durationInMins.filter { it.isDigit() }
        )
    }
}

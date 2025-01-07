package com.example.ui_components.models.measurement.components.metric.variants

import com.example.ui_components.models.measurement.components.metric.Metric
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalMetric: EmbeddedRealmObject {
    var abv: String = ""
    var name: String = ""

    companion object {
        fun mapToOriginal(form: LocalMetric) = Metric(
            abv = form.abv.trim(),
            name = form.name.trim()
        )
    }
}
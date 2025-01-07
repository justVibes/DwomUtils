package com.example.ui_components.models.measurement.variants

import com.example.ui_components.models.measurement.Measurement
import com.example.ui_components.models.measurement.components.metric.Metric
import com.example.ui_components.models.measurement.components.metric.variants.LocalMetric
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalMeasurement : EmbeddedRealmObject {
    var value: String = ""
    var metric: LocalMetric? = null

    companion object {
        fun mapToOriginal(form: LocalMeasurement) = Measurement(
            value = form.value.filter { it.isDigit() },
            metric = form.metric?.let { LocalMetric.mapToOriginal(it) } ?: Metric()
        )
    }
}
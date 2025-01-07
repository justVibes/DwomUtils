package com.example.ui_components.models.measurement.components.metric

import com.example.ui_components.models.measurement.components.metric.variants.LocalMetric

data class Metric(
    val abv: String = "",
    val name: String = ""
) {
    companion object {
        fun mapToLocal(form: Metric) = LocalMetric().apply {
            abv = form.abv.trim()
            name = form.name.trim()
        }
    }
}
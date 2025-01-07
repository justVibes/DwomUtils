package com.example.ui_components.models.measurement

import com.example.ui_components.models.measurement.components.metric.Metric
import com.example.ui_components.models.measurement.variants.LocalMeasurement

data class Measurement(
    val value: String = "",
    val metric: Metric = Metric()
){
    companion object{
        fun mapToOriginal(form: Measurement) = LocalMeasurement().apply {
            value = form.value.filter { it.isDigit() }
            metric = form.metric.let { Metric.mapToLocal(it) }
        }
    }
}
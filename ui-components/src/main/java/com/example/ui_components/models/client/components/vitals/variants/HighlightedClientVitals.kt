package com.example.ui_components.models.client.components.vitals.variants

import com.example.ui_components.models.client.components.core.EditType

data class HighlightedClientVitals(
    val clientId: EditType = EditType.NONE,
    val heartRate: EditType = EditType.NONE,
    val respiratoryRate: EditType = EditType.NONE,
    val bloodPressure: EditType = EditType.NONE,
    val bloodOxygen: EditType = EditType.NONE,
    val bodyTemperatureCel: EditType = EditType.NONE,
)
package com.example.ui_components.models.client.components.medical_info.variants

import com.example.ui_components.models.client.components.core.EditType

data class HighlightedClientMedicalInfo(
    val appointmentReason: EditType = EditType.NONE,
    val prescriptions: EditType = EditType.NONE,
    val recommendations: EditType = EditType.NONE,
    val appointmentDate: EditType = EditType.NONE,
)
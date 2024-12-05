package com.example.ui_components.models.client.components.emergency_contact_info.variants

import com.example.ui_components.models.client.components.core.EditType

data class HighlightedEmergencyContactInfo(
    val name: EditType = EditType.NONE,
    val phoneNumber: EditType = EditType.NONE,
    val email: EditType = EditType.NONE,
    val presentAddress: EditType = EditType.NONE,
    val relationship: EditType = EditType.NONE
)
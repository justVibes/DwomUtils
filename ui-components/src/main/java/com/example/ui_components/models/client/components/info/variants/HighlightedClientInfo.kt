package com.example.ui_components.models.client.components.info.variants

import com.example.ui_components.models.client.components.core.EditType

data class HighlightedClientInfo(
    val tagName: EditType = EditType.NONE,
    val photoUrl: EditType = EditType.NONE,
    val firstName: EditType = EditType.NONE,
    val lastName: EditType = EditType.NONE,
    val sex: EditType = EditType.NONE,
    val birthDate: EditType = EditType.NONE,
    val birthPlace: EditType = EditType.NONE,
    val height: EditType = EditType.NONE,
    val weight: EditType = EditType.NONE,
    val presentAddress: EditType = EditType.NONE,
    val occupation: EditType = EditType.NONE,
    val localPhoneNumber: EditType = EditType.NONE,
    val emailAddress: EditType = EditType.NONE,
    val medicalInfo : EditType = EditType.NONE,
    val financialInfo: EditType = EditType.NONE
)
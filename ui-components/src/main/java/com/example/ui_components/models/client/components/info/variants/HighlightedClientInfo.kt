package com.example.ui_components.models.client.components.info.variants

import com.example.ui_components.models.client.components.core.EditType

data class HighlightedClientInfo(
    var tagName: EditType = EditType.NONE,
    var photoUrl: EditType = EditType.NONE,
    var firstName: EditType = EditType.NONE,
    var lastName: EditType = EditType.NONE,
    var sex: EditType = EditType.NONE,
    val birthDate: EditType = EditType.NONE,
    var birthPlace: EditType = EditType.NONE,
    var height: EditType = EditType.NONE,
    var weight: EditType = EditType.NONE,
    var presentAddress: EditType = EditType.NONE,
    var occupation: EditType = EditType.NONE,
    var localPhoneNumber: EditType = EditType.NONE,
    var emailAddress: EditType = EditType.NONE,
)
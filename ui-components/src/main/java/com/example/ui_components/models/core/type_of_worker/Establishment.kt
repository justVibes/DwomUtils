package com.example.ui_components.models.core.type_of_worker

import java.util.UUID

data class Establishment(
    val photoUrl: String = "",
    val name: String = "",
    val coarseLocation: String = "",
    val typeOfEstablishment: String = "",
    val establishmentId: String = "${UUID.randomUUID()}"
)

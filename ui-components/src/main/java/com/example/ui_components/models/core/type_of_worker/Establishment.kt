package com.example.ui_components.models.core.type_of_worker

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Establishment(
    val establishmentId: String = "${UUID.randomUUID()}",
    val photoUrl: String = "",
    val name: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val workers: List<EstablishmentWorker> = emptyList()
)

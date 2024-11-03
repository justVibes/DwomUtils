package com.example.ui_components.models.core.type_of_worker

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class EstablishmentStripped(
    @PrimaryKey
    val establishmentId: String = "${UUID.randomUUID()}",
    val photoUrl: String = "",
    val name: String = "",
    val coarseLocation: String = "",
    val type: String = ""
)
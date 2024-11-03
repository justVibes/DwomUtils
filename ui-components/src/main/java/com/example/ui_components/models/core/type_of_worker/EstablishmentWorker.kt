package com.example.ui_components.models.core.type_of_worker

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class EstablishmentWorker(
    @PrimaryKey
    val workerId: String = "",
    val name: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val establishmentId: String = ""
)
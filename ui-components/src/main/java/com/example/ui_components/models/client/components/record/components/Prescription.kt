package com.example.ui_components.models.client.components.record.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Entity
data class Prescription(
    @PrimaryKey
    val prescriptionId: String = "${UUID.randomUUID()}",
    val clientId: String = "",
    val type: String = "",
    val brand: String = "",
    val issuedDate: String = "",
    val issuedBy: String = ""
)
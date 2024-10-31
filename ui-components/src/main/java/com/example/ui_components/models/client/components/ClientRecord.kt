package com.example.ui_components.models.client.components

import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.components.Prescription
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ClientRecord(
    @PrimaryKey
    val recordId: String = "${UUID.randomUUID()}",
    val clientId: String = "",
    val medicalIssue: String = "",
    val prescriptions: List<Prescription> = emptyList(),
    val recommendations: List<String> = emptyList(),
    val appointmentDate: String = ""
)
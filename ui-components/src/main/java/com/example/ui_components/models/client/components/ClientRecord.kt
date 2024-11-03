package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.components.Prescription
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Entity
data class ClientRecord(
    @PrimaryKey
    var recordId: String = "${UUID.randomUUID()}",
    var clientId: String = "",
    var medicalIssue: String = "",
    @Ignore var prescriptions: List<Prescription> = emptyList(),
    @Ignore var recommendations: List<String> = emptyList(),
    var appointmentDate: String = "",
    var doctorName: String = "",
    var doctorPhotoUrl: String = ""
)
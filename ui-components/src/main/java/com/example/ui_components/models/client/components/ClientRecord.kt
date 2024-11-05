package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.components.Prescription
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ClientRecord(
    var recordId: String = "${UUID.randomUUID()}",
    var clientId: String = "",
    var medicalIssue: String = "",
    var prescriptions: List<Prescription> = emptyList(),
    var recommendations: List<String> = emptyList(),
    var appointmentDate: String = "",
    var doctorName: String = "",
    var doctorPhotoUrl: String = ""
) {
    object MapToStripped {
        fun from(form: ClientRecord) =
            ClientRecordStripped(
                recordId = form.recordId,
                clientId = form.clientId,
                medicalIssue = form.medicalIssue,
                appointmentDate = form.appointmentDate,
                doctorName = form.doctorName,
                doctorPhotoUrl = form.doctorPhotoUrl
            )
    }
}

@Entity
data class ClientRecordStripped(
    @PrimaryKey
    var recordId: String = "${UUID.randomUUID()}",
    var clientId: String = "",
    var medicalIssue: String = "",
    var appointmentDate: String = "",
    var doctorName: String = "",
    var doctorPhotoUrl: String = ""
){
    object MapToOriginal {
        fun from(form: ClientRecordStripped, prescriptions: List<Prescription>, recommendations: List<String>) =
            ClientRecord(
                recordId = form.recordId,
                clientId = form.clientId,
                medicalIssue = form.medicalIssue,
                appointmentDate = form.appointmentDate,
                doctorName = form.doctorName,
                doctorPhotoUrl = form.doctorPhotoUrl,
                prescriptions = prescriptions,
                recommendations = recommendations
            )
    }
}
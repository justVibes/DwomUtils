package com.example.ui_components.models.client.components.record

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.record.components.Prescription
import com.example.ui_components.models.core.company.components.Employee
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class ClientRecord(
    var recordId: String = "${UUID.randomUUID()}",
    var clientId: String = "",
    var medicalIssue: String = "",
    var prescriptions: List<Prescription> = emptyList(),
    var recommendations: List<String> = emptyList(),
    var appointmentDate: String = "",
    @Transient var serviceProviderDetails: Employee? = null
) {
    object MapToStripped {
        fun from(form: ClientRecord) =
            ClientRecordStripped(
                recordId = form.recordId,
                clientId = form.clientId,
                medicalIssue = form.medicalIssue,
                appointmentDate = form.appointmentDate,
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
        fun from(form: ClientRecordStripped, prescriptions: List<Prescription>, recommendations: List<String>, employeeDetails: Employee) =
            ClientRecord(
                recordId = form.recordId,
                clientId = form.clientId,
                medicalIssue = form.medicalIssue,
                appointmentDate = form.appointmentDate,
                prescriptions = prescriptions,
                recommendations = recommendations,
                serviceProviderDetails = employeeDetails
            )
    }
}


package com.example.ui_components.models.client.components.medical_info

import com.example.ui_components.models.client.components.medical_info.components.prescription.Prescription
import com.example.ui_components.models.client.components.medical_info.components.recommendation.ClientRecommendation
import com.example.ui_components.models.client.components.medical_info.variants.LocalClientMedicalInfo
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable

@Serializable
data class ClientMedicalInfo(
    val patientHealthConcern: String = "",
    val diagnosis: String = "",
    val prescriptions: List<Prescription> = emptyList(),
    val recommendations: List<ClientRecommendation> = emptyList(),
    val appointmentDate: Long = 0L,
) {
    object Config {
        fun mapToLocal(form: ClientMedicalInfo) =
            LocalClientMedicalInfo().apply {
                val formattedFields = trimmedFields(form)
                patientHealthConcern= formattedFields.patientHealthConcern
                diagnosis = formattedFields.diagnosis
                prescriptions =
                    formattedFields.prescriptions
                        .map { Prescription.Config.mapToLocal(it) }
                        .toRealmList()
                recommendations = form.recommendations.map { ClientRecommendation.Config.mapToLocal(it) }.toRealmList()
                appointmentDate = form.appointmentDate
            }

        fun trimmedFields(form: ClientMedicalInfo) = form.copy(
            diagnosis = form.diagnosis.trim(),
            prescriptions = form.prescriptions.map { Prescription.Config.trimmedFields(it) },
            patientHealthConcern = form.patientHealthConcern.trim()
        )
    }
}




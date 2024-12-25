package com.example.ui_components.models.client.components.medical_info

import com.example.ui_components.models.client.components.medical_info.components.prescription.Prescription
import com.example.ui_components.models.client.components.medical_info.components.recommendation.ClientRecommendation
import com.example.ui_components.models.client.components.medical_info.variants.LocalClientMedicalInfo
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable

@Serializable
data class ClientMedicalInfo(
    val healthConcern: String = "",
    val diagnosis: String = "",
    val prescriptions: List<Prescription> = emptyList(),
    val recommendations: List<ClientRecommendation> = emptyList(),
    val appointmentDate: Long = 0L,
) {
    companion object {
        fun mapToLocal(form: ClientMedicalInfo) =
            LocalClientMedicalInfo().apply {
                val fmtForm = trimmedFields(form)
                healthConcern = fmtForm.healthConcern
                diagnosis = fmtForm.diagnosis
                prescriptions =
                    fmtForm.prescriptions
                        .map { Prescription.mapToLocal(it) }
                        .toRealmList()
                recommendations =
                    fmtForm.recommendations.map { ClientRecommendation.mapToLocal(it) }
                        .toRealmList()
                appointmentDate = fmtForm.appointmentDate
            }

        fun trimmedFields(form: ClientMedicalInfo) = form.copy(
            diagnosis = form.diagnosis.trim(),
            prescriptions = form.prescriptions.map { Prescription.trimmedFields(it) },
            healthConcern = form.healthConcern.trim(),
            recommendations = form.recommendations.map { it.copy(recommendation = it.recommendation.trim()) },
            appointmentDate = form.appointmentDate
        )
    }
}




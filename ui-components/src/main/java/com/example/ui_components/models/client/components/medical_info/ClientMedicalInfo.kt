package com.example.ui_components.models.client.components.medical_info

import com.example.ui_components.models.client.components.medical_info.components.Prescription
import com.example.ui_components.models.client.components.medical_info.variants.LocalClientMedicalInfo
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable

@Serializable
data class ClientMedicalInfo(
    val appointmentReason: String = "",
    val prescriptions: List<Prescription> = emptyList(),
    val recommendations: List<String> = emptyList(),
    val appointmentDate: Long = 0L,
) {
    object Config {
        fun mapToLocal(form: ClientMedicalInfo) =
            LocalClientMedicalInfo().apply {
                val formattedFields = trimmedFields(form)
                appointmentReason = formattedFields.appointmentReason
                prescriptions =
                    formattedFields.prescriptions
                        .map { Prescription.Config.mapToLocal(it) }
                        .toRealmList()
                recommendations = formattedFields.recommendations.toRealmList()
                appointmentDate = form.appointmentDate
            }

        fun trimmedFields(form: ClientMedicalInfo) = form.copy(
            appointmentReason = form.appointmentReason,
            prescriptions = form.prescriptions.map { Prescription.Config.trimmedFields(it) },
            recommendations = form.recommendations.map { it.trim() },
        )
    }
}




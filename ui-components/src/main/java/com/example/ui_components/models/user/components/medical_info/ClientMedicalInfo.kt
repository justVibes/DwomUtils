package com.example.ui_components.models.user.components.medical_info

import com.example.ui_components.models.user.components.medical_info.components.lab_result.LabResult
import com.example.ui_components.models.user.components.medical_info.components.metadata.ClientMedicalInfoMetadata
import com.example.ui_components.models.user.components.medical_info.components.note.ClientNote
import com.example.ui_components.models.user.components.medical_info.components.prescription.Prescription
import com.example.ui_components.models.user.components.medical_info.components.recommendation.ClientRecommendation
import com.example.ui_components.models.user.components.medical_info.components.vitals.ClientVitals
import com.example.ui_components.models.user.components.medical_info.variants.LocalClientMedicalInfo
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class ClientMedicalInfo(
    val seenByAssistant: Boolean = false,
    val diagnosis: String = "",
    val prescriptions: List<Prescription> = emptyList(),
    val recommendations: List<ClientRecommendation> = emptyList(),
    val metadata: ClientMedicalInfoMetadata = ClientMedicalInfoMetadata(),
    val vitals: ClientVitals = ClientVitals(),
    @Transient val labResults: List<LabResult> = emptyList(),
    val notes: List<ClientNote> = emptyList()
) {
    companion object {
        fun mapToLocal(form: ClientMedicalInfo) = LocalClientMedicalInfo().apply {
            val fmtForm = trimmedFields(form)
            diagnosis = fmtForm.diagnosis
            prescriptions = fmtForm.prescriptions.map { Prescription.mapToLocal(it) }.toRealmList()
            recommendations = fmtForm.recommendations.map { ClientRecommendation.mapToLocal(it) }
                .toRealmList()
            metadata = fmtForm.metadata.let { ClientMedicalInfoMetadata.mapToLocal(it) }
            vitals = fmtForm.vitals.let { ClientVitals.mapToLocal(it) }
            labResults = fmtForm.labResults.map { LabResult.mapToLocal(it) }.toRealmList()
            notes = fmtForm.notes.map { ClientNote.mapToLocal(it) }.toRealmList()
        }

        fun trimmedFields(form: ClientMedicalInfo) = form.copy(
            diagnosis = form.diagnosis.trim(),
            prescriptions = form.prescriptions.map { Prescription.trimmedFields(it) },
            recommendations = form.recommendations.map { it.copy(recommendation = it.recommendation.trim()) },
            metadata = form.metadata.let { ClientMedicalInfoMetadata.trimmedFields(it) },
            vitals = form.vitals.let { ClientVitals.trimmedFields(it) },
            labResults = form.labResults,
            notes = form.notes.map { ClientNote.trimmedFields(it) }
        )
    }
}




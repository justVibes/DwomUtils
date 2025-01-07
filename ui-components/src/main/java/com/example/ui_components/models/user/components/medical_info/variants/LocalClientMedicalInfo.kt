package com.example.ui_components.models.user.components.medical_info.variants

import com.example.ui_components.models.user.components.medical_info.ClientMedicalInfo
import com.example.ui_components.models.user.components.medical_info.components.lab_result.variants.LocalLabResult
import com.example.ui_components.models.user.components.medical_info.components.metadata.ClientMedicalInfoMetadata
import com.example.ui_components.models.user.components.medical_info.components.metadata.variants.LocalClientMedicalInfoMetadata
import com.example.ui_components.models.user.components.medical_info.components.note.variants.LocalClientNote
import com.example.ui_components.models.user.components.medical_info.components.prescription.variants.LocalPrescription
import com.example.ui_components.models.user.components.medical_info.components.recommendation.variants.LocalClientRecommendation
import com.example.ui_components.models.user.components.medical_info.components.vitals.ClientVitals
import com.example.ui_components.models.user.components.medical_info.components.vitals.variants.LocalClientVitals
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientMedicalInfo : EmbeddedRealmObject {
    var diagnosis: String = ""
    var prescriptions: RealmList<LocalPrescription> = realmListOf()
    var recommendations: RealmList<LocalClientRecommendation> = realmListOf()
    var metadata: LocalClientMedicalInfoMetadata? = null
    var vitals: LocalClientVitals? = null
    var labResults: RealmList<LocalLabResult> = realmListOf()
    var notes: RealmList<LocalClientNote> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalClientMedicalInfo): ClientMedicalInfo {
            val fmtForm = trimmedFields(form)
            return ClientMedicalInfo(
                diagnosis = fmtForm.diagnosis,
                prescriptions = fmtForm.prescriptions.map {
                    LocalPrescription.mapToOriginal(it)
                },
                recommendations = fmtForm.recommendations.map {
                    LocalClientRecommendation.mapToOriginal(it)
                },
                metadata = fmtForm.metadata?.let { LocalClientMedicalInfoMetadata.mapToOriginal(it) }
                    ?: ClientMedicalInfoMetadata(),
                vitals = fmtForm.vitals?.let { LocalClientVitals.mapToOriginal(it) }
                    ?: ClientVitals(),
                labResults = fmtForm.labResults.map { LocalLabResult.mapToOriginal(it) },
                notes = fmtForm.notes.map { LocalClientNote.mapToOriginal(it) }
            )
        }

        fun trimmedFields(form: LocalClientMedicalInfo) = LocalClientMedicalInfo().apply {
            diagnosis = form.diagnosis.trim()
            prescriptions = form.prescriptions.map { LocalPrescription.trimmedFields(it) }
                .toRealmList()
            recommendations =
                form.recommendations.map { LocalClientRecommendation.trimmedFields(it) }
                    .toRealmList()
            metadata = form.metadata?.let { LocalClientMedicalInfoMetadata.trimmedFields(it) }
            vitals = form.vitals?.let { LocalClientVitals.trimmedFields(it) }
            labResults = form.labResults
            notes = form.notes.map { LocalClientNote.trimmedFields(it) }.toRealmList()
        }
    }
}
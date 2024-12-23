package com.example.ui_components.models.client.components.medical_info.variants

import com.example.ui_components.models.client.components.medical_info.ClientMedicalInfo
import com.example.ui_components.models.client.components.medical_info.components.prescription.variants.LocalPrescription
import com.example.ui_components.models.client.components.medical_info.components.recommendation.variants.LocalClientRecommendation
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientMedicalInfo : EmbeddedRealmObject {
    var appointmentReason: String = ""
    var diagnosis: String = ""
    var prescriptions: RealmList<LocalPrescription> = realmListOf()
    var recommendations: RealmList<LocalClientRecommendation> = realmListOf()
    var appointmentDate: Long = 0L

    companion object {
        fun mapToOriginal(form: LocalClientMedicalInfo): ClientMedicalInfo {
            val formattedFields = trimmedFields(form)
            return ClientMedicalInfo(
                appointmentReason = formattedFields.appointmentReason,
                diagnosis = formattedFields.diagnosis,
                prescriptions = formattedFields.prescriptions.map {
                    LocalPrescription.mapToOriginal(it)
                },
                recommendations = formattedFields.recommendations.map {
                    LocalClientRecommendation.mapToOriginal(it)
                },
                appointmentDate = form.appointmentDate
            )
        }

        fun trimmedFields(form: LocalClientMedicalInfo) = LocalClientMedicalInfo().apply {
            diagnosis = form.diagnosis.trim()
            appointmentReason = form.appointmentReason.trim()
            prescriptions =
                form.prescriptions.map { LocalPrescription.trimmedFields(it) }.toRealmList()
            recommendations =
                form.recommendations
                    .map {
                        LocalClientRecommendation()
                            .apply {
                                uid = it.uid.trim()
                                recommendation = it.recommendation.trim()
                                issueDate = it.issueDate
                            }
                    }
                    .toRealmList()
        }
    }
}
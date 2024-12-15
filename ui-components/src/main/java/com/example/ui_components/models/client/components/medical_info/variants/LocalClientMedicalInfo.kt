package com.example.ui_components.models.client.components.medical_info.variants

import com.example.ui_components.models.client.components.medical_info.ClientMedicalInfo
import com.example.ui_components.models.client.components.medical_info.components.prescription.variants.LocalPrescription
import com.example.ui_components.models.client.components.medical_info.components.recommendation.variants.LocalClientRecommendation
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientMedicalInfo : EmbeddedRealmObject {
    var patientHealthConcern: String = ""
    var diagnosis: String = ""
    var prescriptions: RealmList<LocalPrescription> = realmListOf()
    var recommendations: RealmList<LocalClientRecommendation> = realmListOf()
    var appointmentDate: Long = 0L

    object Config {
        fun mapToOriginal(form: LocalClientMedicalInfo): ClientMedicalInfo {
            val formattedFields = trimmedFields(form)
            return ClientMedicalInfo(
                patientHealthConcern = formattedFields.patientHealthConcern,
                diagnosis = formattedFields.diagnosis,
                prescriptions = formattedFields.prescriptions.map {
                    LocalPrescription.Config.mapToOriginal(it)
                },
                recommendations = formattedFields.recommendations.map {
                    LocalClientRecommendation.Config.mapToOriginal(it)
                },
                appointmentDate = form.appointmentDate
            )
        }

        fun trimmedFields(form: LocalClientMedicalInfo) = LocalClientMedicalInfo().apply {
            diagnosis = form.diagnosis.trim()
            patientHealthConcern = form.patientHealthConcern.trim()
            prescriptions =
                form.prescriptions.map { LocalPrescription.Config.trimmedFields(it) }.toRealmList()
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
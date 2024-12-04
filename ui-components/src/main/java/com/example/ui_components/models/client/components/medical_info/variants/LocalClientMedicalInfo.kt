package com.example.ui_components.models.client.components.medical_info.variants

import com.example.ui_components.models.client.components.medical_info.ClientMedicalInfo
import com.example.ui_components.models.client.components.medical_info.components.variants.LocalPrescription
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientMedicalInfo : EmbeddedRealmObject {
    var appointmentReason: String = ""
    var prescriptions: RealmList<LocalPrescription> = realmListOf()
    var recommendations: RealmList<String> = realmListOf()
    var appointmentDate: Long = 0L

    object Config {
        fun mapToOriginal(form: LocalClientMedicalInfo): ClientMedicalInfo {
            val formattedFields = trimmedFields(form)
            return ClientMedicalInfo(
                appointmentReason = formattedFields.appointmentReason,
                prescriptions = formattedFields.prescriptions.map {
                    LocalPrescription.Config.mapToOriginal(it)
                },
                recommendations = formattedFields.recommendations,
                appointmentDate = form.appointmentDate
            )
        }

        fun trimmedFields(form: LocalClientMedicalInfo) = form.apply {
            appointmentReason = appointmentReason.trim()
            prescriptions =
                prescriptions.map { LocalPrescription.Config.trimmedFields(it) }.toRealmList()
            recommendations = recommendations.map { it.trim() }.toRealmList()
        }
    }
}
package com.example.ui_components.models.client.components.medical_info.components.prescription.variants

import com.example.ui_components.models.client.components.medical_info.components.prescription.Prescription
import com.example.ui_components.models.client.components.medical_info.components.prescription.components.LocalPrescriptionInscription
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPrescription : EmbeddedRealmObject {
    var prescriptionId: String = ""
    var refills: String = ""
    var inscription: LocalPrescriptionInscription? = null
    var signatura: String = ""
    var subscription: String = ""
    var issuedDate: Long = 0L
    var issuedBy: String = ""

    object Config {
        fun mapToOriginal(form: LocalPrescription): Prescription {
            val formattedForm = trimmedFields(form)
            return Prescription(
                prescriptionId = formattedForm.prescriptionId,
                refills = formattedForm.refills,
                inscription = LocalPrescriptionInscription.Config.mapToLocal(
                    formattedForm.inscription ?: LocalPrescriptionInscription()
                ),
                signatura = formattedForm.signatura,
                subscription = formattedForm.subscription,
                issuedBy = formattedForm.issuedBy,
                issuedDate = formattedForm.issuedDate
            )
        }

        fun trimmedFields(form: LocalPrescription) = LocalPrescription().apply {
            prescriptionId = form.prescriptionId.trim()
            refills = form.refills.trim()
            inscription = LocalPrescriptionInscription.Config.trimmedFields(
                form.inscription ?: LocalPrescriptionInscription()
            )
            signatura = form.signatura.trim()
            subscription = form.subscription.trim()
            issuedBy = form.issuedBy.trim()
        }
    }
}
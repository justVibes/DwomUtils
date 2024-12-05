package com.example.ui_components.models.client.components.medical_info.components.prescription

import com.example.ui_components.models.client.components.medical_info.components.prescription.components.PrescriptionInscription
import com.example.ui_components.models.client.components.medical_info.components.prescription.variants.LocalPrescription
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Prescription(
    val prescriptionId: String = "${UUID.randomUUID()}",
    val refills: String = "",
    val inscription: PrescriptionInscription = PrescriptionInscription(),
    val signatura: String = "",
    val subscription: String = "",
    val issuedDate: Long = 0L,
    val issuedBy: String = ""
) {
    object Config {
        fun mapToLocal(form: Prescription): LocalPrescription {
            val formattedForm = trimmedFields(form)
            return LocalPrescription().apply {
                prescriptionId = formattedForm.prescriptionId
                refills = formattedForm.refills
                inscription = PrescriptionInscription.Config.mapToLocal(formattedForm.inscription)
                signatura = formattedForm.signatura
                subscription = formattedForm.subscription
                issuedDate = formattedForm.issuedDate
                issuedBy = formattedForm.issuedBy
            }
        }

        fun trimmedFields(form: Prescription) = Prescription(
            prescriptionId = form.prescriptionId.trim(),
            refills = form.refills.trim(),
            inscription = PrescriptionInscription.Config.trimmedFields(form.inscription),
            signatura = form.signatura.trim(),
            subscription = form.subscription.trim(),
            issuedBy = form.issuedBy.trim(),
        )
    }
}



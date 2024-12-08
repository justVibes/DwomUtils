package com.example.ui_components.models.client.components.medical_info.components.prescription.components.medicine

import com.example.ui_components.models.client.components.medical_info.components.prescription.components.inscription.PrescriptionInscription
import com.example.ui_components.models.client.components.medical_info.components.prescription.components.medicine.variants.LocalPrescriptionMedicine
import kotlinx.serialization.Serializable

@Serializable
data class PrescriptionMedicine(
    val refills: String = "0",
    val inscription: PrescriptionInscription = PrescriptionInscription(),
    val signatura: String = "",
    val subscription: String = "",
){
    object Config {
        fun mapToLocal(form: PrescriptionMedicine) = LocalPrescriptionMedicine().apply {
            val formattedForm = trimmedFields(form)
            refills = formattedForm.refills
            inscription = PrescriptionInscription.Config.mapToLocal(formattedForm.inscription)
            signatura = formattedForm.signatura
            subscription = formattedForm.subscription
        }
        
        fun trimmedFields(form: PrescriptionMedicine) = form.copy(
            refills = form.refills.trim(),
            inscription = PrescriptionInscription.Config.trimmedFields(form.inscription),
            signatura = form.signatura.trim(),
            subscription = form.subscription.trim()
        )
    }
}
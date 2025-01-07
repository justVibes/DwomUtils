package com.example.ui_components.models.user.components.medical_info.components.prescription.components.inscription

import com.example.ui_components.models.user.components.medical_info.components.prescription.components.inscription.variants.LocalPrescriptionInscription
import kotlinx.serialization.Serializable

@Serializable
data class PrescriptionInscription(
    val drugName: String = "",
    val quantity: String = ""
){
    companion object {
        fun mapToLocal(form: PrescriptionInscription) = LocalPrescriptionInscription().apply {
            val formattedForm = trimmedFields(form)
            drugName = formattedForm.drugName
            quantity = formattedForm.quantity
        }
        
        fun trimmedFields(form: PrescriptionInscription) = form.copy(
            drugName = form.drugName.trim(),
            quantity = form.quantity.trim()
        )
    }
}
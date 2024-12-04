package com.example.ui_components.models.client.components.medical_info.components

import com.example.ui_components.models.client.components.medical_info.components.variants.LocalPrescription
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Prescription(
    val prescriptionId: String = "${UUID.randomUUID()}",
    val type: String = "",
    val brand: String = "",
    val issuedDate: Long = 0L,
    val issuedBy: String = ""
) {
    object Config {
        fun mapToLocal(form: Prescription): LocalPrescription {
            val formattedForm = trimmedFields(form)
            return LocalPrescription().apply {
                prescriptionId = formattedForm.prescriptionId
                type = formattedForm.type
                brand = formattedForm.type
                issuedDate = formattedForm.issuedDate
                issuedBy = formattedForm.issuedBy
            }
        }

        fun trimmedFields(form: Prescription) = Prescription(
            prescriptionId = form.prescriptionId.trim(),
            type = form.type.trim(),
            brand = form.brand.trim(),
            issuedBy = form.issuedBy.trim(),
        )
    }
}



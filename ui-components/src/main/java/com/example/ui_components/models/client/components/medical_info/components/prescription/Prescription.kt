package com.example.ui_components.models.client.components.medical_info.components.prescription

import com.example.ui_components.models.client.components.medical_info.components.prescription.variants.LocalPrescription
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Prescription(
    val prescriptionId: String = "${UUID.randomUUID()}",
    val refills: String = "",
    val brand: String = "",
    val amount: String = "",
    val issuedDate: Long = 0L,
    val issuedBy: String = ""
) {
    object Config {
        fun mapToLocal(form: Prescription): LocalPrescription {
            val formattedForm = trimmedFields(form)
            return LocalPrescription().apply {
                prescriptionId = formattedForm.prescriptionId
                type = formattedForm.refills
                brand = formattedForm.refills
                issuedDate = formattedForm.issuedDate
                issuedBy = formattedForm.issuedBy
            }
        }

        fun trimmedFields(form: Prescription) = Prescription(
            prescriptionId = form.prescriptionId.trim(),
            refills = form.refills.trim(),
            brand = form.brand.trim(),
            issuedBy = form.issuedBy.trim(),
        )
    }
}



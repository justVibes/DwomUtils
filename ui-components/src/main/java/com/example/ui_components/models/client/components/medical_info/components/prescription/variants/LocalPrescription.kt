package com.example.ui_components.models.client.components.medical_info.components.prescription.variants

import com.example.ui_components.models.client.components.medical_info.components.prescription.Prescription
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPrescription : EmbeddedRealmObject {
    var prescriptionId: String = ""
    var type: String = ""
    var brand: String = ""
    var issuedDate: Long = 0L
    var issuedBy: String = ""

    object Config {
        fun mapToOriginal(form: LocalPrescription) = Prescription(
            prescriptionId = form.prescriptionId,
            refills = form.type,
            brand = form.brand,
            issuedBy = form.issuedBy,
            issuedDate = form.issuedDate
        )

        fun trimmedFields(form: LocalPrescription) = LocalPrescription().apply {
            prescriptionId = form.prescriptionId.trim()
            type = form.type.trim()
            brand = form.brand.trim()
            issuedBy = form.issuedBy.trim()
        }
    }
}
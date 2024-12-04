package com.example.ui_components.models.client.components.medical_info.components.variants

import com.example.ui_components.models.client.components.medical_info.components.Prescription
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
            type = form.type,
            brand = form.brand,
            issuedBy = form.issuedBy,
            issuedDate = form.issuedDate
        )

        fun trimmedFields(form: LocalPrescription) = form.apply {
            prescriptionId = prescriptionId.trim()
            type = type.trim()
            brand = brand.trim()
            issuedBy = issuedBy.trim()
        }
    }
}
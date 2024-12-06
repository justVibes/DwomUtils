package com.example.ui_components.models.client.components.medical_info.components.prescription.components.inscription.variants

import com.example.ui_components.models.client.components.medical_info.components.prescription.components.inscription.PrescriptionInscription
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPrescriptionInscription: EmbeddedRealmObject {
    var drugName: String = ""
    var quantity: String = ""

    object Config{
        fun mapToLocal(form: LocalPrescriptionInscription): PrescriptionInscription {
            val formattedForm = trimmedFields(form)
            return PrescriptionInscription(
                drugName = formattedForm.drugName,
                quantity = formattedForm.quantity
            )
        }

        fun trimmedFields(form: LocalPrescriptionInscription) = LocalPrescriptionInscription().apply {
            drugName = form.drugName.trim()
            quantity = form.quantity.trim()
        }
    }
}
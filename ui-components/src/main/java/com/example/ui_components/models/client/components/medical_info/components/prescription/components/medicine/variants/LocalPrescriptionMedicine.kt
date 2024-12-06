package com.example.ui_components.models.client.components.medical_info.components.prescription.components.medicine.variants

import com.example.ui_components.models.client.components.medical_info.components.prescription.components.inscription.variants.LocalPrescriptionInscription
import com.example.ui_components.models.client.components.medical_info.components.prescription.components.medicine.PrescriptionMedicine
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPrescriptionMedicine : EmbeddedRealmObject {
    var refills: String = ""
    var inscription: LocalPrescriptionInscription? = null
    var signatura: String = ""
    var subscription: String = ""
    
    object Config {
        fun mapToOriginal(form: LocalPrescriptionMedicine): PrescriptionMedicine {
            val formattedForm = trimmedFields(form)
            return PrescriptionMedicine(
                refills = formattedForm.refills,
                inscription = LocalPrescriptionInscription.Config.mapToLocal(formattedForm.inscription ?: LocalPrescriptionInscription()),
                signatura = formattedForm.signatura,
                subscription = formattedForm.subscription
            )
        }
        
        fun trimmedFields(form: LocalPrescriptionMedicine) = LocalPrescriptionMedicine().apply { 
            refills = form.refills.trim()
            inscription = LocalPrescriptionInscription.Config.trimmedFields(form.inscription ?: LocalPrescriptionInscription())
            signatura = form.signatura.trim()
            subscription = form.subscription.trim()
        }
    }
}
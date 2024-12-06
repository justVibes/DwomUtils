package com.example.ui_components.models.client.components.medical_info.components.prescription.variants

import com.example.ui_components.models.client.components.medical_info.components.prescription.Prescription
import com.example.ui_components.models.client.components.medical_info.components.prescription.components.medicine.variants.LocalPrescriptionMedicine
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalPrescription : EmbeddedRealmObject {
    var prescriptionId: String = ""
    var medicines: RealmList<LocalPrescriptionMedicine> = realmListOf()
    var clientName: String = ""
    var clientAddress: String = ""
    var issuedDate: Long = 0L
    var issuedBy: String = ""

    object Config {
        fun mapToOriginal(form: LocalPrescription): Prescription {
            val formattedForm = trimmedFields(form)
            return Prescription(
                prescriptionId = formattedForm.prescriptionId,
                medicines = formattedForm.medicines.map { LocalPrescriptionMedicine.Config.mapToOriginal(it) },
                clientName = formattedForm.clientName,
                clientAddress = formattedForm.clientAddress,
                issuedBy = formattedForm.issuedBy,
                issuedDate = formattedForm.issuedDate
            )
        }

        fun trimmedFields(form: LocalPrescription) = LocalPrescription().apply {
            prescriptionId = form.prescriptionId.trim()
            medicines = form.medicines.map { LocalPrescriptionMedicine.Config.trimmedFields(it) }.toRealmList()
            clientName = form.clientName.trim()
            clientAddress = form.clientAddress.trim()
            issuedBy = form.issuedBy.trim()
        }
    }
}
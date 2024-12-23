package com.example.ui_components.models.client.components.medical_info.components.prescription

import com.example.ui_components.models.client.components.medical_info.components.prescription.components.inscription.PrescriptionInscription
import com.example.ui_components.models.client.components.medical_info.components.prescription.components.medicine.PrescriptionMedicine
import com.example.ui_components.models.client.components.medical_info.components.prescription.variants.LocalPrescription
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Prescription(
    val prescriptionId: String = "${UUID.randomUUID()}",
    val medicines: List<PrescriptionMedicine> = emptyList(),
    val clientName: String = "",
    val clientAddress: String = "",
    val issuedDate: Long = 0L,
    val issuedBy: String = "",
) {
    companion object {
        fun mapToLocal(form: Prescription): LocalPrescription {
            val formattedForm = trimmedFields(form)
            return LocalPrescription().apply {
                prescriptionId = formattedForm.prescriptionId
                medicines = formattedForm.medicines.map { PrescriptionMedicine.mapToLocal(it) }.toRealmList()
                clientName = formattedForm.clientName
                clientAddress = formattedForm.clientAddress
                issuedDate = formattedForm.issuedDate
                issuedBy = formattedForm.issuedBy
            }
        }

        fun trimmedFields(form: Prescription) = Prescription(
            prescriptionId = form.prescriptionId.trim(),
            medicines = form.medicines.map {
                it.copy(
                    refills = it.refills.trim(),
                    inscription = PrescriptionInscription.trimmedFields(it.inscription),
                    signatura = it.signatura.trim(),
                    subscription = it.subscription.trim()
                )
            },
            clientName = form.clientName,
            clientAddress = form.clientAddress,
            issuedBy = form.issuedBy.trim(),
        )
    }
}



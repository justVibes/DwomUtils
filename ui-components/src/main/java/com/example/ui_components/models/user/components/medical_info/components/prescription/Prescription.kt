package com.example.ui_components.models.user.components.medical_info.components.prescription

import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.user.components.medical_info.components.prescription.components.inscription.PrescriptionInscription
import com.example.ui_components.models.user.components.medical_info.components.prescription.components.medicine.PrescriptionMedicine
import com.example.ui_components.models.user.components.medical_info.components.prescription.variants.LocalPrescription
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Prescription(
    val uid: String = "${UUID.randomUUID()}",
    val medicines: List<PrescriptionMedicine> = emptyList(),
    val clientName: String = "",
    val clientAddress: String = "",
    val dateIssued: Long = 0L,
    val issuedBy: PublicEmployeeInfo = PublicEmployeeInfo(),
) {
    companion object {
        fun mapToLocal(form: Prescription): LocalPrescription {
            val fmtForm = trimmedFields(form)
            return LocalPrescription().apply {
                uid = fmtForm.uid
                medicines = fmtForm.medicines.map { PrescriptionMedicine.mapToLocal(it) }.toRealmList()
                clientName = fmtForm.clientName
                clientAddress = fmtForm.clientAddress
                dateIssued = fmtForm.dateIssued
                issuedBy = fmtForm.issuedBy.let { PublicEmployeeInfo.mapToLocal(it) }
            }
        }

        fun trimmedFields(form: Prescription) = Prescription(
            uid = form.uid.trim(),
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
            issuedBy = form.issuedBy.let { PublicEmployeeInfo.trimmedFields(it) },
        )
    }
}



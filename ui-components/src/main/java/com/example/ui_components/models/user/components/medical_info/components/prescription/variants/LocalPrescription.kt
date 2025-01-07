package com.example.ui_components.models.user.components.medical_info.components.prescription.variants

import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.components.public_info.variants.LocalPublicEmployeeInfo
import com.example.ui_components.models.user.components.medical_info.components.prescription.Prescription
import com.example.ui_components.models.user.components.medical_info.components.prescription.components.medicine.variants.LocalPrescriptionMedicine
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalPrescription : EmbeddedRealmObject {
    var uid: String = ""
    var medicines: RealmList<LocalPrescriptionMedicine> = realmListOf()
    var clientName: String = ""
    var clientAddress: String = ""
    var dateIssued: Long = 0L
    var issuedBy: LocalPublicEmployeeInfo? = null

    companion object {
        fun mapToOriginal(form: LocalPrescription): Prescription {
            val fmtForm = trimmedFields(form)
            return Prescription(
                uid = fmtForm.uid,
                medicines = fmtForm.medicines.map { LocalPrescriptionMedicine.mapToOriginal(it) },
                clientName = fmtForm.clientName,
                clientAddress = fmtForm.clientAddress,
                dateIssued = fmtForm.dateIssued,
                issuedBy = fmtForm.issuedBy?.let { LocalPublicEmployeeInfo.mapToOriginal(it) }
                    ?: PublicEmployeeInfo()
            )
        }

        fun trimmedFields(form: LocalPrescription) = LocalPrescription().apply {
            uid = form.uid.trim()
            medicines = form.medicines.map { LocalPrescriptionMedicine.trimmedFields(it) }.toRealmList()
            clientName = form.clientName.trim()
            clientAddress = form.clientAddress.trim()
            dateIssued = form.dateIssued
            issuedBy = form.issuedBy?.let { LocalPublicEmployeeInfo.trimmedFields(it) }
        }
    }
}
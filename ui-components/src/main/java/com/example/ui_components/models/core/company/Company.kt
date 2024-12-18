package com.example.ui_components.models.core.company

import com.example.ui_components.models.core.company.components.company_summary.CompanySummary
import com.example.ui_components.models.core.company.components.doctor.Doctor
import com.example.ui_components.models.core.company.variants.LocalCompany
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.toRealmList
import java.util.UUID

data class Company(
    val companyId: String = "${UUID.randomUUID()}",
    val summary: CompanySummary? = null,
    val photoUrl: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val aboutUs: String = "",
    var employeesDocPaths: List<String> = emptyList(),
    @Exclude var doctors: List<Doctor> = emptyList()/*This is for local usage*/
) {
    object Config {
        fun mapToLocal(form: Company) = LocalCompany().apply {
            val formattedForm = trimmedFields(form)
            companyId = formattedForm.companyId
            summary = formattedForm.summary?.let { CompanySummary.Config.mapToLocal(it) }
            photoUrl = formattedForm.photoUrl
            coarseLocation = formattedForm.coarseLocation
            type = formattedForm.type
            aboutUs = formattedForm.aboutUs
            employeesDocPaths = formattedForm.employeesDocPaths.toRealmList()
            employees = formattedForm.doctors.map { Doctor.Config.mapToLocal(it) }.toRealmList()
        }

        fun trimmedFields(form: Company) = Company(
            companyId = form.companyId.trim(),
            summary = CompanySummary.Config.trimmedFields(form.summary),
            photoUrl = form.photoUrl.trim(),
            coarseLocation = form.coarseLocation.trim(),
            type = form.type.trim(),
            aboutUs = form.aboutUs.trim(),
            employeesDocPaths = form.employeesDocPaths.map { it.trim() },
            doctors = form.doctors.map { Doctor.Config.trimmedFields(it) }
        )
    }
}

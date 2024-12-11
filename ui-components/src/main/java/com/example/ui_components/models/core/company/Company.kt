package com.example.ui_components.models.core.company

import com.example.ui_components.models.core.company.components.company_summary.CompanySummary
import com.example.ui_components.models.core.company.components.company_summary.variants.LocalCompanySummary
import com.example.ui_components.models.core.company.components.employee.Employee
import com.example.ui_components.models.core.company.components.employee.variants.LocalEmployee
import com.example.ui_components.models.core.company.variants.LocalCompany
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import java.util.UUID

data class Company(
    val companyId: String = "${UUID.randomUUID()}",
    val summary: CompanySummary? = null,
    val photoUrl: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val aboutUs: String = "",
    var employeesDocPaths: List<String> = emptyList(),
    @Exclude var employees: List<Employee> = emptyList()/*This is for local usage*/
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
            employees = formattedForm.employees.map { Employee.Config.mapToLocal(it) }.toRealmList()
        }

        fun trimmedFields(form: Company) = Company(
            companyId = form.companyId.trim(),
            summary = CompanySummary.Config.trimmedFields(form.summary),
            photoUrl = form.photoUrl.trim(),
            coarseLocation = form.coarseLocation.trim(),
            type = form.type.trim(),
            aboutUs = form.aboutUs.trim(),
            employeesDocPaths = form.employeesDocPaths.map { it.trim() },
            employees = form.employees.map { Employee.Config.trimmedFields(it) }
        )
    }
}

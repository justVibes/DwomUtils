package com.example.ui_components.models.core.company

import com.example.ui_components.models.core.company.components.employee.Employee
import com.example.ui_components.models.core.company.components.metadata.CompanyMetadata
import com.example.ui_components.models.core.company.variants.LocalCompany
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.toRealmList
import java.util.UUID

data class Company(
    val companyId: String = "${UUID.randomUUID()}",
    val metadata: CompanyMetadata = CompanyMetadata(),
    val employeesDocPaths: List<String> = emptyList(),
    @Exclude val employees: List<Employee> = emptyList() /*This is for local usage*/
) {
    companion object {
        fun mapToLocal(form: Company) = LocalCompany().apply {
            val fmtForm = trimmedFields(form)
            companyId = fmtForm.companyId
            metadata = CompanyMetadata.mapToLocal(fmtForm.metadata)
            employeesDocPaths = fmtForm.employeesDocPaths.toRealmList()
            employees = fmtForm.employees.map { Employee.mapToLocal(it) }.toRealmList()
        }

        fun trimmedFields(form: Company) = Company(
            companyId = form.companyId.trim(),
            metadata = CompanyMetadata.trimmedFields(form.metadata),
            employeesDocPaths = form.employeesDocPaths.map { it.trim() },
            employees = form.employees.map { Employee.trimmedFields(it) }
        )
    }
}

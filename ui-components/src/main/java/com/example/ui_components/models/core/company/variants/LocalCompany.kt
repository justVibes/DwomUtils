package com.example.ui_components.models.core.company.variants

import com.example.ui_components.models.core.company.Company
import com.example.ui_components.models.core.company.components.employee.variants.LocalEmployee
import com.example.ui_components.models.core.company.components.metadata.variants.LocalCompanyMetadata
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import java.util.UUID

class LocalCompany : EmbeddedRealmObject {
    var companyId: String = "${UUID.randomUUID()}"
    var metadata: LocalCompanyMetadata? = null
    var employeesDocPaths: RealmList<String> = realmListOf()
    @Exclude
    var employees: RealmList<LocalEmployee> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalCompany): Company {
            val fmtForm = trimmedFields(form)
            return Company(
                companyId = fmtForm.companyId,
                metadata =  LocalCompanyMetadata.mapToOriginal(fmtForm.metadata ?: LocalCompanyMetadata()),
                employeesDocPaths = fmtForm.employeesDocPaths,
                employees = fmtForm.employees.map { LocalEmployee.mapToOriginal(it) }
            )
        }

        fun trimmedFields(form: LocalCompany) = LocalCompany().apply {
            companyId = form.companyId.trim()
            metadata = form.metadata?.let { LocalCompanyMetadata.trimmedFields(it) }
            employeesDocPaths = form.employeesDocPaths.map { it.trim() }.toRealmList()
            employees = form.employees.map { LocalEmployee.trimmedFields(it) }.toRealmList()
        }
    }
}
package com.example.ui_components.models.core.company.components.metadata.components.collection_paths.variants

import com.example.ui_components.models.core.company.components.metadata.components.collection_paths.CompanyCollectionPaths
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalCompanyCollectionPaths : EmbeddedRealmObject {
    var employees: String = ""
    var clients: String = ""
    var company: String = ""

    companion object {
        fun mapToOriginal(form: LocalCompanyCollectionPaths): CompanyCollectionPaths {
            val fmtForm = trimmedFields(form)
            return CompanyCollectionPaths(
                employees = fmtForm.employees,
                clients = fmtForm.clients,
                company = fmtForm.company
            )
        }

        fun trimmedFields(form: LocalCompanyCollectionPaths) = LocalCompanyCollectionPaths().apply {
            employees = form.employees.trim()
            clients = form.clients.trim()
            company = form.company.trim()
        }
    }
}
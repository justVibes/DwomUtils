package com.example.ui_components.models.core.company.components.metadata.components.collection_paths

import com.example.ui_components.models.core.company.components.metadata.components.collection_paths.variants.LocalCompanyCollectionPaths

data class CompanyCollectionPaths(
    val employees: String = "",
    val clients: String = "",
    val company: String = ""
) {
    companion object {
        fun mapToLocal(form: CompanyCollectionPaths) = LocalCompanyCollectionPaths().apply {
            val fmtForm = trimmedFields(form)
            employees = fmtForm.employees
            clients = fmtForm.clients
            company = fmtForm.company
        }

        fun trimmedFields(form: CompanyCollectionPaths) = form.copy(
            employees = form.employees.trim(),
            clients = form.clients.trim(),
            company = form.company.trim()
        )
    }
}
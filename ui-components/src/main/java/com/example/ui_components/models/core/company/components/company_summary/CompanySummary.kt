package com.example.ui_components.models.core.company.components.company_summary

import com.example.ui_components.models.core.company.components.company_summary.variants.LocalCompanySummary

data class CompanySummary(
    val name: String = "",
    val category: String = "",
    val collectionPath: String? = null
) {
    object Config {
        fun mapToLocal(form: CompanySummary?): LocalCompanySummary? {
            return trimmedFields(form)?.let { formattedForm ->
                LocalCompanySummary().apply {
                    name = formattedForm.name
                    category = formattedForm.category
                    collectionPath = formattedForm.collectionPath
                }
            }
        }

        fun trimmedFields(form: CompanySummary?) = form?.copy(
            name = form.name.trim(),
            category = form.category.trim(),
            collectionPath = form.collectionPath?.trim()
        )
    }
}



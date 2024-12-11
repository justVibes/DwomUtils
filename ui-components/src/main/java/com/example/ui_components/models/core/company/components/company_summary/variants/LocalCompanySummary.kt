package com.example.ui_components.models.core.company.components.company_summary.variants

import com.example.ui_components.models.core.company.components.company_summary.CompanySummary
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalCompanySummary : EmbeddedRealmObject {
    var name: String = ""
    var category: String = ""
    var collectionPath: String? = null

    object Config {
        fun mapToOriginal(form: LocalCompanySummary): CompanySummary {
            val formattedForm = trimmedFields(form)
            return CompanySummary(
                name = formattedForm.name,
                category = formattedForm.category,
                collectionPath = formattedForm.collectionPath
            )
        }

        fun trimmedFields(form: LocalCompanySummary) = LocalCompanySummary().apply {
            name = form.name.trim()
            category = form.category.trim()
            collectionPath = form.collectionPath?.trim()
        }
    }
}
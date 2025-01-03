package com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.components.company_attributes

import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.components.company_attributes.variants.LocalCompanyAttributes
import io.realm.kotlin.ext.toRealmList

data class CompanyAttributes(
    val fields: List<String> = emptyList(),
    val types: List<String> = emptyList()
) {
    companion object {
        fun mapToLocal(form: CompanyAttributes) = LocalCompanyAttributes().apply {
            fields = form.fields.map { it.trim() }.toRealmList()
            types = form.types.map { it.trim() }.toRealmList()
        }
    }
}
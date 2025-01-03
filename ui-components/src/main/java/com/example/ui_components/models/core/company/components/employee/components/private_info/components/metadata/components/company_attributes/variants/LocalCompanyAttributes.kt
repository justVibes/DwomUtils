package com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.components.company_attributes.variants

import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.components.company_attributes.CompanyAttributes
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalCompanyAttributes : EmbeddedRealmObject {
    var fields: RealmList<String> = realmListOf()
    var types: RealmList<String> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalCompanyAttributes) = CompanyAttributes(
            fields = form.fields.map { it.trim() },
            types = form.types.map { it.trim() }
        )
    }
}
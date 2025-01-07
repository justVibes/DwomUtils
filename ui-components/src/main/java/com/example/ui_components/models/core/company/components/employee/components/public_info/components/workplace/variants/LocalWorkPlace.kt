package com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.variants

import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.WorkPlace
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalWorkPlace : EmbeddedRealmObject {
    var companyCollectionPath: String? = null
    var name: String = ""
    var field: String = ""
    var type: String = ""

    companion object {
        fun mapToOriginal(form: LocalWorkPlace): WorkPlace {
            val fmtForm = trimmedFields(form)
            return WorkPlace(
                companyCollectionPath = fmtForm.companyCollectionPath,
                name = fmtForm.name,
                field = fmtForm.field,
                type = fmtForm.type
            )
        }

        fun trimmedFields(form: LocalWorkPlace) = LocalWorkPlace().apply {
            companyCollectionPath = form.companyCollectionPath?.trim()
            name = form.name.trim()
            field = form.field.trim()
            type = form.type.trim()
        }
    }
}
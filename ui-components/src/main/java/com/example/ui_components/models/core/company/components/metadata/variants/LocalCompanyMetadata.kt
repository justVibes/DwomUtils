package com.example.ui_components.models.core.company.components.metadata.variants

import com.example.ui_components.models.core.company.components.metadata.CompanyMetadata
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalCompanyMetadata : EmbeddedRealmObject {
    var name: String = ""
    var aboutUs: String = ""
    var field: String = ""
    var type: String = ""
    var photoUrl: String = ""
    var collectionPath: String = ""
    var coarseLocation: String = ""

    companion object {
        fun mapToOriginal(form: LocalCompanyMetadata): CompanyMetadata {
            val fmtForm = trimmedFields(form)
            return CompanyMetadata(
                name = fmtForm.name,
                aboutUs = fmtForm.aboutUs,
                type = fmtForm.type,
                field = fmtForm.field,
                photoUrl = fmtForm.photoUrl,
                collectionPath = fmtForm.collectionPath,
                coarseLocation = fmtForm.coarseLocation
            )
        }

        fun trimmedFields(form: LocalCompanyMetadata) = LocalCompanyMetadata().apply {
            name = form.name.trim()
            aboutUs = form.aboutUs.trim()
            type = form.type.trim()
            field = form.field.trim()
            photoUrl = form.photoUrl.trim()
            collectionPath = form.collectionPath.trim()
            coarseLocation = form.coarseLocation.trim()
        }
    }
}
package com.example.ui_components.models.core.company.components.metadata.variants

import com.example.ui_components.models.core.company.components.metadata.CompanyMetadata
import com.example.ui_components.models.core.company.components.metadata.components.collection_paths.CompanyCollectionPaths
import com.example.ui_components.models.core.company.components.metadata.components.collection_paths.variants.LocalCompanyCollectionPaths
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalCompanyMetadata : EmbeddedRealmObject {
    var name: String = ""
    var aboutUs: String = ""
    var field: String = ""
    var type: String = ""
    var photoUrl: String = ""
    var collectionPaths: LocalCompanyCollectionPaths? = null
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
                collectionPaths = fmtForm.collectionPaths?.let {
                    LocalCompanyCollectionPaths.mapToOriginal(it)
                } ?: CompanyCollectionPaths(),
                coarseLocation = fmtForm.coarseLocation
            )
        }

        fun trimmedFields(form: LocalCompanyMetadata) = LocalCompanyMetadata().apply {
            name = form.name.trim()
            aboutUs = form.aboutUs.trim()
            type = form.type.trim()
            field = form.field.trim()
            photoUrl = form.photoUrl.trim()
            collectionPaths =
                form.collectionPaths?.let { LocalCompanyCollectionPaths.trimmedFields(it) }
            coarseLocation = form.coarseLocation.trim()
        }
    }
}
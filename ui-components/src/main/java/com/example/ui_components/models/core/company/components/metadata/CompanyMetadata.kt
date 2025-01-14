package com.example.ui_components.models.core.company.components.metadata

import com.example.ui_components.models.core.company.components.metadata.components.collection_paths.CompanyCollectionPaths
import com.example.ui_components.models.core.company.components.metadata.variants.LocalCompanyMetadata


data class CompanyMetadata(
    val name: String = "",
    val aboutUs: String = "",
    val field: String = "", /* Use 'CompanyFields.label' to initialize */
    val type: String = "", /* Use a value from the 'CompanyFields.types' list to initialize */
    val photoUrl: String = "",
    val collectionPaths: CompanyCollectionPaths = CompanyCollectionPaths(),
    val coarseLocation: String = "",
) {
    companion object {
        fun mapToLocal(form: CompanyMetadata) = LocalCompanyMetadata().apply {
            val fmtForm = trimmedFields(form)
            name = fmtForm.name
            aboutUs = fmtForm.aboutUs
            field = fmtForm.field
            type = fmtForm.type
            photoUrl = fmtForm.photoUrl
            collectionPaths = fmtForm.collectionPaths.let { CompanyCollectionPaths.mapToLocal(it) }
            coarseLocation = fmtForm.coarseLocation
        }

        fun trimmedFields(form: CompanyMetadata) = form.copy(
            name = form.name.trim(),
            aboutUs = form.aboutUs.trim(),
            field = form.field.trim(),
            type = form.type.trim(),
            photoUrl = form.photoUrl.trim(),
            collectionPaths = form.collectionPaths.let { CompanyCollectionPaths.trimmedFields(it) },
            coarseLocation = form.coarseLocation.trim()
        )
    }
}
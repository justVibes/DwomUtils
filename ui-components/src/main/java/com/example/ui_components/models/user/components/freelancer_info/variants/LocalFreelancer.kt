package com.example.ui_components.models.user.components.freelancer_info.variants

import com.example.ui_components.models.user.components.freelancer_info.FreelancerInfo
import com.example.ui_components.models.user.components.freelancer_info.components.metadata.variants.LocalFreelancerMetadata
import com.example.ui_components.models.user.components.freelancer_info.components.portfolio.variants.LocalPortfolioFile
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalFreelancer : EmbeddedRealmObject {
    var rating: Double = 0.0
    var metadata: LocalFreelancerMetadata? = null
    var portfolio: RealmList<LocalPortfolioFile> = realmListOf()
    var metadataDocPath: String = ""

    companion object {
        fun mapToOriginal(form: LocalFreelancer): FreelancerInfo {
            val fmtForm = trimmedFields(form)
            return FreelancerInfo(
                rating = fmtForm.rating,
                metadata = fmtForm.metadata.let {
                    LocalFreelancerMetadata.mapToOriginal(it ?: LocalFreelancerMetadata())
                },
                metadataDocPath = fmtForm.metadataDocPath,
                portfolio = form.portfolio.map { LocalPortfolioFile.mapToOriginal(it) }
            )
        }

        fun trimmedFields(form: LocalFreelancer) = LocalFreelancer().apply {
            rating = form.rating
            metadata = form.metadata
            metadataDocPath = form.metadataDocPath.trim()
            portfolio = form.portfolio.map { LocalPortfolioFile.trimmedFields(it) }.toRealmList()
        }
    }
}
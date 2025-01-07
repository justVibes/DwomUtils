package com.example.ui_components.models.user.components.freelancer_info

import com.example.ui_components.models.user.components.freelancer_info.components.metadata.FreelancerMetadata
import com.example.ui_components.models.user.components.freelancer_info.components.portfolio.PortfolioFile
import com.example.ui_components.models.user.components.freelancer_info.variants.LocalFreelancer
import io.realm.kotlin.ext.toRealmList


data class FreelancerInfo(
    val rating: Double = 0.0,
    val portfolio: List<PortfolioFile> = emptyList(),
    val metadata: FreelancerMetadata = FreelancerMetadata(),
    val metadataDocPath: String = ""
) {
    companion object {
        fun mapToLocal(form: FreelancerInfo) = LocalFreelancer().apply {
            val fmtForm = trimmedFields(form)
            rating = fmtForm.rating
            metadata = fmtForm.metadata.let { FreelancerMetadata.mapToLocal(it) }
            portfolio = form.portfolio.map { PortfolioFile.mapToLocal(it) }.toRealmList()

        }

        fun trimmedFields(form: FreelancerInfo) = form.copy(
            rating = form.rating,
            metadata = form.metadata,
            portfolio = form.portfolio.map { PortfolioFile.trimmedFields(it) },
            metadataDocPath = form.metadataDocPath.trim()
        )
    }
}
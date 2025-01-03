package com.example.ui_components.models.core.freelancer

import com.example.ui_components.models.core.freelancer.components.metadata.FreelancerMetadata
import com.example.ui_components.models.core.freelancer.components.portfolio.PortfolioFile
import com.example.ui_components.models.core.freelancer.variants.LocalFreelancer
import io.realm.kotlin.ext.toRealmList


data class Freelancer(
    val uid: String = "",
    val tagName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val phoneNumber: String = "",
    val rating: Double = 0.0,
    val metadata: FreelancerMetadata = FreelancerMetadata(),
    val portfolio: List<PortfolioFile> = emptyList()
) {
    companion object {
        fun mapToLocal(form: Freelancer) = LocalFreelancer().apply {
            val fmtForm = trimmedFields(form)
            uid = fmtForm.uid
            tagName = fmtForm.tagName
            email = fmtForm.email
            photoUrl = fmtForm.photoUrl
            phoneNumber = fmtForm.phoneNumber
            rating = fmtForm.rating
            metadata = fmtForm.metadata.let { FreelancerMetadata.mapToLocal(it) }
            portfolio = form.portfolio.map { PortfolioFile.mapToLocal(it) }.toRealmList()
        }

        fun trimmedFields(form: Freelancer) = form.copy(
            uid = form.uid.trim(),
            tagName = form.tagName.trim(),
            email = form.email.trim(),
            photoUrl = form.photoUrl.trim(),
            phoneNumber = form.phoneNumber.filter { it.isDigit() },
            rating = form.rating,
            metadata = form.metadata,
            portfolio = form.portfolio.map { PortfolioFile.trimmedFields(it) }
        )
    }
}
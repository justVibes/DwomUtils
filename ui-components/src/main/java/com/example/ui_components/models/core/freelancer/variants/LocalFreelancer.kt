package com.example.ui_components.models.core.freelancer.variants

import com.example.ui_components.models.core.freelancer.Freelancer
import com.example.ui_components.models.core.freelancer.components.metadata.variants.LocalFreelancerMetadata
import com.example.ui_components.models.core.freelancer.components.portfolio.variants.LocalPortfolioFile
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalFreelancer : EmbeddedRealmObject {
    var uid: String = ""
    var tagName: String = ""
    var email: String = ""
    var photoUrl: String = ""
    var phoneNumber: String = ""
    var rating: Double = 0.0
    var metadata: LocalFreelancerMetadata? = null
    var portfolio: RealmList<LocalPortfolioFile> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalFreelancer): Freelancer {
            val fmtForm = trimmedFields(form)
            return Freelancer(
                uid = fmtForm.uid,
                tagName = fmtForm.tagName,
                email = fmtForm.email,
                photoUrl = fmtForm.photoUrl,
                phoneNumber = fmtForm.phoneNumber,
                rating = fmtForm.rating,
                metadata = fmtForm.metadata.let {
                    LocalFreelancerMetadata.mapToOriginal(it ?: LocalFreelancerMetadata())
                },
                portfolio = form.portfolio.map { LocalPortfolioFile.mapToOriginal(it) }
            )
        }

        fun trimmedFields(form: LocalFreelancer) = LocalFreelancer().apply {
            uid = form.uid.trim()
            photoUrl = form.photoUrl.trim()
            tagName = form.tagName.trim()
            email = form.email.trim()
            phoneNumber = form.phoneNumber.filter { it.isDigit() }.trim()
            rating = form.rating
            metadata = form.metadata
            portfolio = form.portfolio.map { LocalPortfolioFile.trimmedFields(it) }.toRealmList()
        }
    }
}
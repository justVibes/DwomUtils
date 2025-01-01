package com.example.ui_components.models.core.freelancer.variants

import com.example.ui_components.models.core.freelancer.Freelancer
import com.example.ui_components.models.core.freelancer.components.portfolio.variants.LocalPortfolioFile
import com.example.ui_components.models.core.type_of_worker.components.worker_title.variants.LocalWorkerTitle
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class LocalFreelancer : RealmObject {
    @PrimaryKey
    var uid: String = ""
    var tagName: String = ""
    var email: String = ""
    var title: LocalWorkerTitle? = null
    var photoUrl: String = ""
    var phoneNumber: String = ""
    var rating: Double = 0.0
    var typeOfAppointment: String = ""
    var endProduct: String = ""
    var portfolio: RealmList<LocalPortfolioFile> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalFreelancer): Freelancer {
            val fmtForm = trimmedFields(form)
            return Freelancer(
                uid = fmtForm.uid,
                tagName = fmtForm.tagName,
                email = fmtForm.email,
                title = LocalWorkerTitle.mapToOriginal(fmtForm.title ?: LocalWorkerTitle()),
                photoUrl = fmtForm.photoUrl,
                phoneNumber = fmtForm.phoneNumber,
                rating = fmtForm.rating,
                typeOfAppointment = fmtForm.typeOfAppointment,
                endProduct = fmtForm.endProduct,
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
            title = form.title?.let { LocalWorkerTitle.trimmedFields(it) }
            typeOfAppointment = form.typeOfAppointment.trim()
            endProduct = form.endProduct.trim()
            portfolio = form.portfolio.map { LocalPortfolioFile.trimmedFields(it) }.toRealmList()
        }
    }
}
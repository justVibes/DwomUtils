package com.example.ui_components.models.core.freelancer

import com.example.ui_components.models.core.freelancer.components.portfolio.PortfolioFile
import com.example.ui_components.models.core.freelancer.variants.LocalFreelancer
import com.example.ui_components.models.core.type_of_worker.components.worker_title.WorkerTitle
import io.realm.kotlin.ext.toRealmList

data class Freelancer(
    val uid: String = "",
    val tagName: String = "",
    val email: String = "",
    val title: WorkerTitle = WorkerTitle(),
    val photoUrl: String = "",
    val phoneNumber: String = "",
    val rating: Double = 0.0,
    val typeOfAppointment: String = "",
    val endProduct: String = "",
    val portfolio: List<PortfolioFile> = emptyList()
) {
    companion object {
        fun mapToLocal(form: Freelancer) = LocalFreelancer().apply {
            val fmtForm = trimmedFields(form)
            uid = fmtForm.uid
            tagName = fmtForm.tagName
            email = fmtForm.email
            title = WorkerTitle.mapToLocal(fmtForm.title)
            photoUrl = fmtForm.photoUrl
            phoneNumber = fmtForm.phoneNumber
            rating = fmtForm.rating
            typeOfAppointment = fmtForm.typeOfAppointment
            endProduct = fmtForm.endProduct
            portfolio = form.portfolio.map { PortfolioFile.mapToLocal(it) }.toRealmList()
        }

        fun trimmedFields(form: Freelancer) = form.copy(
            uid = form.uid.trim(),
            tagName = form.tagName.trim(),
            email = form.email.trim(),
            title = WorkerTitle.trimmedFields(form.title),
            photoUrl = form.photoUrl.trim(),
            phoneNumber = form.phoneNumber.filter { it.isDigit() },
            rating = form.rating,
            typeOfAppointment = form.typeOfAppointment.trim(),
            endProduct = form.endProduct.trim(),
            portfolio = form.portfolio.map { PortfolioFile.trimmedFields(it) }
        )
    }
}
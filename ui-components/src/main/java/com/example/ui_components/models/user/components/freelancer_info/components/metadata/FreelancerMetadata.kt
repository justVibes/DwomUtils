package com.example.ui_components.models.user.components.freelancer_info.components.metadata

import com.example.ui_components.models.core.worker_title.WorkTitle
import com.example.ui_components.models.payment_methods.components.payment_info.PaymentInfo
import com.example.ui_components.models.user.components.freelancer_info.components.metadata.variants.LocalFreelancerMetadata


/* This class is initialized by the server */
data class FreelancerMetadata(
    val title: WorkTitle = WorkTitle(),
    val iconUrl: String = "",
    val endProduct: String = "",
    val typeOfAppointment: String = "",
    val baseRate: PaymentInfo = PaymentInfo()
) {
    companion object {
        fun mapToLocal(form: FreelancerMetadata) = LocalFreelancerMetadata().apply {
            title = form.title.let { WorkTitle.mapToLocal(it) }
            iconUrl = form.iconUrl.trim()
            endProduct = form.endProduct.trim()
            typeOfAppointment = form.typeOfAppointment.trim()
            baseRate = PaymentInfo.mapToLocal(form.baseRate)
        }
    }
}
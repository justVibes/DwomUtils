package com.example.ui_components.models.core.freelancer.components.metadata

import com.example.ui_components.models.core.freelancer.components.metadata.variants.LocalFreelancerMetadata
import com.example.ui_components.models.core.worker_title.WorkerTitle
import com.example.ui_components.models.payment_methods.components.payment_info.PaymentInfo


/* This class is initialized by the server */
data class FreelancerMetadata(
    val title: WorkerTitle = WorkerTitle(),
    val iconUrl: String = "",
    val endProduct: String = "",
    val typeOfAppointment: String = "",
    val baseRate: PaymentInfo = PaymentInfo()
) {
    companion object {
        fun mapToLocal(form: FreelancerMetadata) = LocalFreelancerMetadata().apply {
            title = form.title.let { WorkerTitle.mapToLocal(it) }
            iconUrl = form.iconUrl.trim()
            endProduct = form.endProduct.trim()
            typeOfAppointment = form.typeOfAppointment.trim()
            baseRate = PaymentInfo.mapToLocal(form.baseRate)
        }
    }
}
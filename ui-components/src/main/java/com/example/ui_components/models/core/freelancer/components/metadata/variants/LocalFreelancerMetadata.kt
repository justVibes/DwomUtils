package com.example.ui_components.models.core.freelancer.components.metadata.variants

import com.example.ui_components.models.core.freelancer.components.metadata.FreelancerMetadata
import com.example.ui_components.models.core.worker_title.variants.LocalWorkerTitle
import com.example.ui_components.models.payment_methods.components.payment_info.variants.LocalPaymentInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalFreelancerMetadata : EmbeddedRealmObject {
    var title: LocalWorkerTitle? = null
    var iconUrl: String = ""
    var endProduct: String = ""
    var typeOfAppointment: String = ""
    var baseRate: LocalPaymentInfo? = null

    companion object {
        fun mapToOriginal(form: LocalFreelancerMetadata) = FreelancerMetadata(
            title = form.title.let { LocalWorkerTitle.mapToOriginal(it ?: LocalWorkerTitle()) },
            iconUrl = form.iconUrl.trim(),
            endProduct = form.endProduct.trim(),
            typeOfAppointment = form.typeOfAppointment.trim(),
            baseRate = LocalPaymentInfo.mapToOriginal(form.baseRate ?: LocalPaymentInfo())
        )
    }
}
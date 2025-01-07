package com.example.ui_components.models.user.components.freelancer_info.components.metadata.variants

import com.example.ui_components.models.core.worker_title.variants.LocalWorkTitle
import com.example.ui_components.models.payment_methods.components.payment_info.variants.LocalPaymentInfo
import com.example.ui_components.models.user.components.freelancer_info.components.metadata.FreelancerMetadata
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalFreelancerMetadata : EmbeddedRealmObject {
    var title: LocalWorkTitle? = null
    var iconUrl: String = ""
    var endProduct: String = ""
    var typeOfAppointment: String = ""
    var baseRate: LocalPaymentInfo? = null

    companion object {
        fun mapToOriginal(form: LocalFreelancerMetadata) = FreelancerMetadata(
            title = form.title.let { LocalWorkTitle.mapToOriginal(it ?: LocalWorkTitle()) },
            iconUrl = form.iconUrl.trim(),
            endProduct = form.endProduct.trim(),
            typeOfAppointment = form.typeOfAppointment.trim(),
            baseRate = LocalPaymentInfo.mapToOriginal(form.baseRate ?: LocalPaymentInfo())
        )
    }
}
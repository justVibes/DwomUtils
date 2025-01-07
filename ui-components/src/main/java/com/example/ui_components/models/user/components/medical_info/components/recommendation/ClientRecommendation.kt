package com.example.ui_components.models.user.components.medical_info.components.recommendation

import com.example.ui_components.models.user.components.medical_info.components.recommendation.variants.LocalClientRecommendation
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ClientRecommendation(
    val uid: String = "${UUID.randomUUID()}",
    val recommendation: String = "",
) {
    companion object {
        fun mapToLocal(form: ClientRecommendation) = LocalClientRecommendation().apply {
            uid = form.uid
            recommendation = form.recommendation.trim()
        }
    }
}
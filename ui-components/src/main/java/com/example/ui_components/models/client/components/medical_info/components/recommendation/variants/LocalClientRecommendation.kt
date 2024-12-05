package com.example.ui_components.models.client.components.medical_info.components.recommendation.variants

import com.example.ui_components.models.client.components.medical_info.components.recommendation.ClientRecommendation
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientRecommendation : EmbeddedRealmObject {
    var recommendationId: String = ""
    var recommendation: String = ""
    var issueDate: Long = 0L

    object Config {
        fun mapToOriginal(form: LocalClientRecommendation) = ClientRecommendation(
            recommendationId = form.recommendationId,
            recommendation = form.recommendation.trim(),
            issueDate = form.issueDate
        )
    }
}
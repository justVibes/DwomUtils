package com.example.ui_components.models.user.components.medical_info.components.recommendation.variants

import com.example.ui_components.models.user.components.medical_info.components.recommendation.ClientRecommendation
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientRecommendation : EmbeddedRealmObject {
    var uid: String = ""
    var recommendation: String = ""

    companion object {
        fun mapToOriginal(form: LocalClientRecommendation): ClientRecommendation {
            val fmtForm = trimmedFields(form)
            return ClientRecommendation(
                uid = fmtForm.uid,
                recommendation = fmtForm.recommendation
            )
        }

        fun trimmedFields(form: LocalClientRecommendation) = LocalClientRecommendation().apply {
            uid = form.uid.trim()
            recommendation = form.recommendation.trim()
        }
    }
}
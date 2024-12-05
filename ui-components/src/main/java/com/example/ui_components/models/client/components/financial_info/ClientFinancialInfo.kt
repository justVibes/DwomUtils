package com.example.ui_components.models.client.components.financial_info

import com.example.ui_components.models.client.components.core.stringComparison
import com.example.ui_components.models.client.components.financial_info.variants.HighlightedClientFinancialInfo
import com.example.ui_components.models.client.components.financial_info.variants.LocalClientFinancialInfo
import kotlinx.serialization.Serializable

@Serializable
data class ClientFinancialInfo(
    val insuranceCarrier: String = "",
    val insurancePlan: String = "",
    val contactNumber: String = "",
    val policyNumber: String = "",
) {
    object Config {
        fun mapToLocal(form: ClientFinancialInfo) = LocalClientFinancialInfo().apply {
            val formattedForm = trimmedFields(form)
            insuranceCarrier = formattedForm.insuranceCarrier
            insurancePlan = formattedForm.insurancePlan
            contactNumber = formattedForm.contactNumber
            policyNumber = formattedForm.policyNumber
        }

        fun mapToHighlighted(original: ClientFinancialInfo, modified: ClientFinancialInfo) =
            HighlightedClientFinancialInfo(
                insurancePlan = stringComparison(original.insurancePlan, modified.insurancePlan),
                insuranceCarrier = stringComparison(
                    original.insuranceCarrier,
                    modified.insuranceCarrier
                ),
                contactNumber = stringComparison(original.contactNumber, modified.contactNumber),
                policyNumber = stringComparison(original.policyNumber, modified.policyNumber)
            )

        fun trimmedFields(form: ClientFinancialInfo) = form.copy(
            insuranceCarrier = form.insuranceCarrier.trim(),
            insurancePlan = form.insurancePlan.trim(),
            contactNumber = form.contactNumber.trim(),
            policyNumber = form.policyNumber.trim()
        )
    }
}




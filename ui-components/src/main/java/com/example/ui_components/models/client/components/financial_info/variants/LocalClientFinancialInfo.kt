package com.example.ui_components.models.client.components.financial_info.variants

import com.example.ui_components.models.client.components.financial_info.ClientFinancialInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientFinancialInfo : EmbeddedRealmObject {
    var insuranceCarrier: String = ""
    var insurancePlan: String = ""
    var contactNumber: String = ""
    var policyNumber: String = ""

    object Config {
        fun mapToOriginal(form: LocalClientFinancialInfo): ClientFinancialInfo {
            val formattedForm = trimmedFields(form)
            return ClientFinancialInfo(
                insuranceCarrier = formattedForm.insuranceCarrier,
                insurancePlan = formattedForm.insurancePlan,
                contactNumber = formattedForm.contactNumber,
                policyNumber = formattedForm.policyNumber
            )
        }

        fun trimmedFields(form: LocalClientFinancialInfo) = form.apply {
            insuranceCarrier = insuranceCarrier.trim()
            insurancePlan = insurancePlan.trim()
            contactNumber = contactNumber.trim()
            policyNumber = policyNumber.trim()
        }
    }
}
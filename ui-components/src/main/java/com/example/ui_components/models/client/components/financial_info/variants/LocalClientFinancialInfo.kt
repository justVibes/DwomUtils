package com.example.ui_components.models.client.components.financial_info.variants

import com.example.ui_components.models.client.components.financial_info.ClientFinancialInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientFinancialInfo : EmbeddedRealmObject {
    var insuranceCarrier: String = ""
    var insurancePlan: String = ""
    var contactNumber: String = ""
    var policyNumber: String = ""

    companion object {
        fun mapToOriginal(form: LocalClientFinancialInfo): ClientFinancialInfo {
            val formattedForm = trimmedFields(form)
            return ClientFinancialInfo(
                insuranceCarrier = formattedForm.insuranceCarrier,
                insurancePlan = formattedForm.insurancePlan,
                contactNumber = formattedForm.contactNumber,
                policyNumber = formattedForm.policyNumber
            )
        }

        fun trimmedFields(form: LocalClientFinancialInfo) = LocalClientFinancialInfo().apply {
            insuranceCarrier = form.insuranceCarrier.trim()
            insurancePlan = form.insurancePlan.trim()
            contactNumber = form.contactNumber.trim()
            policyNumber = form.policyNumber.trim()
        }
    }
}
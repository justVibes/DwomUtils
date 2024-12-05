package com.example.ui_components.models.client.components.financial_info.variants

import com.example.ui_components.models.client.components.core.EditType

data class HighlightedClientFinancialInfo(
    val insuranceCarrier: EditType = EditType.NONE,
    val insurancePlan: EditType = EditType.NONE,
    val contactNumber: EditType = EditType.NONE,
    val policyNumber: EditType = EditType.NONE,
)
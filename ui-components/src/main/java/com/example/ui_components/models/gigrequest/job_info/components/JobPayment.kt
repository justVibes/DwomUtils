package com.example.ui_components.models.gigrequest.job_info.components

data class JobPayment(
    val type: String? = null, /* Use the 'JobPaymentType' enum to initialize */
    /* If min-max budget is initialized then static price must not be initialized */
    val minBudget: String = "",
    val maxBudget: String = "",

    /* If static price is initialized then min-max budget must not be initialized */
    val staticPrice: String = ""
)

enum class JobPaymentType {
    CASH, CARD
}
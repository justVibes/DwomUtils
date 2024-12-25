package com.example.ui_components.models.payment_methods.components

import com.example.ui_components.models.payment_methods.variants.components.LocalPaymentCurrency

/* Use the 'PaymentCurrencyConfig' enum to initialize */
data class PaymentCurrency(
    val symbol: String = "",
    val name: String = ""
) {
    companion object {
        fun mapToLocal(form: PaymentCurrency) = LocalPaymentCurrency().apply {
            val fmtForm = trimmedFields(form)
            symbol = fmtForm.symbol
            name = fmtForm.name
        }

        fun trimmedFields(form: PaymentCurrency) = form.copy(
            symbol = form.symbol.trim(),
            name = form.name.trim()
        )
    }
}











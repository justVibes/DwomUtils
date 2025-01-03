package com.example.ui_components.models.payment_methods.components.payment_currency

import com.example.ui_components.models.payment_methods.components.payment_currency.variants.LocalPaymentCurrency
import com.example.ui_components.models.payment_methods.core.PaymentCurrencyConfig

/* Use the 'PaymentCurrencyConfig' enum to initialize */
data class PaymentCurrency(
    val symbol: String = PaymentCurrencyConfig.JMD.symbol,
    val name: String = PaymentCurrencyConfig.JMD.name
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











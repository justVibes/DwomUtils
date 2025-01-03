package com.example.ui_components.models.payment_methods.components.payment_currency.variants

import com.example.ui_components.models.payment_methods.components.payment_currency.PaymentCurrency
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPaymentCurrency : EmbeddedRealmObject {
    var symbol: String = ""
    var name: String = ""

    companion object {
        fun mapToOriginal(form: LocalPaymentCurrency): PaymentCurrency {
            val fmtForm = trimmedFields(form)
            return PaymentCurrency(
                symbol = fmtForm.symbol,
                name = fmtForm.name
            )
        }
        fun trimmedFields(form: LocalPaymentCurrency) = LocalPaymentCurrency().apply {
            symbol = form.symbol.trim()
            name = form.name.trim()
        }
    }
}
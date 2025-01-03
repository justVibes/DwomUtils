package com.example.ui_components.models.payment_methods.components.payment_info

import com.example.ui_components.models.payment_methods.components.payment_currency.PaymentCurrency
import com.example.ui_components.models.payment_methods.components.payment_info.variants.LocalPaymentInfo

data class PaymentInfo(
    val amt: String = "",
    val currency: PaymentCurrency = PaymentCurrency()
){
    companion object{
        fun mapToLocal(form: PaymentInfo) = LocalPaymentInfo().apply {
            amt = form.amt.filter { it.isDigit() }
            currency = PaymentCurrency.mapToLocal(form.currency)
        }
    }
}
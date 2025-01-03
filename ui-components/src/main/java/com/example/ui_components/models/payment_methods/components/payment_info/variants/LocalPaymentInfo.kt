package com.example.ui_components.models.payment_methods.components.payment_info.variants

import com.example.ui_components.models.payment_methods.components.payment_currency.variants.LocalPaymentCurrency
import com.example.ui_components.models.payment_methods.components.payment_info.PaymentInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPaymentInfo: EmbeddedRealmObject {
    var amt: String = ""
    var currency: LocalPaymentCurrency? = null
    
    companion object{
        fun mapToOriginal(form: LocalPaymentInfo) = PaymentInfo(
            amt = form.amt.filter { it.isDigit() },
            currency = LocalPaymentCurrency.mapToOriginal(form.currency ?: LocalPaymentCurrency())
        )
    }
}
package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment.variants

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment.ClientPayment
import com.example.ui_components.models.payment_methods.variants.LocalPaymentMethod
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientPayment : EmbeddedRealmObject {
    var cash: LocalPaymentMethod.LocalCash? = null
    var card: LocalPaymentMethod.LocalCard? = null
    var cheque: LocalPaymentMethod.LocalBankCheque? = null
    var paymentDate: Long = 0L

    companion object {
        fun mapToOriginal(form: LocalClientPayment): ClientPayment {
            val fmtForm = trimmedFields(form)
            return ClientPayment(
                cash = fmtForm.cash?.let { LocalPaymentMethod.LocalCash.mapToOriginal(it) },
                card = fmtForm.card?.let { LocalPaymentMethod.LocalCard.mapToOriginal(it) },
                cheque = fmtForm.cheque?.let { LocalPaymentMethod.LocalBankCheque.mapToOriginal(it) },
                paymentDate = fmtForm.paymentDate
            )
        }

        fun trimmedFields(form: LocalClientPayment) = LocalClientPayment().apply {
            cash = form.cash
            card = form.card?.let { LocalPaymentMethod.LocalCard.trimmedFields(it) }
            cheque = form.cheque?.let { LocalPaymentMethod.LocalBankCheque.trimmedFields(it) }
            paymentDate = form.paymentDate
        }
    }
}
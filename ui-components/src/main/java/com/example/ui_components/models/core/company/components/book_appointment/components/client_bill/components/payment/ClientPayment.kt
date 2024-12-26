package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment.variants.LocalClientPayment
import com.example.ui_components.models.payment_methods.PaymentMethod

data class ClientPayment(
    val cash: PaymentMethod.Cash? = null,
    val card: PaymentMethod.Card? = null,
    val cheque: PaymentMethod.BankCheque? = null,
    val paymentDate: Long = 0L
) {
    companion object {
        fun mapToLocal(form: ClientPayment) = LocalClientPayment().apply {
            val fmtForm = trimmedFields(form)
            cash = fmtForm.cash?.let { PaymentMethod.Cash.mapToLocal(it) }
            card = fmtForm.card?.let { PaymentMethod.Card.mapToLocal(it) }
            cheque = fmtForm.cheque?.let { PaymentMethod.BankCheque.mapToLocal(it) }
            paymentDate = fmtForm.paymentDate
        }

        fun trimmedFields(form: ClientPayment) = form.copy(
            cash = form.cash?.let { PaymentMethod.Cash.trimmedFields(it) },
            card = form.card?.let { PaymentMethod.Card.trimmedFields(it) },
            cheque = form.cheque?.let { PaymentMethod.BankCheque.trimmedFields(it) },
            paymentDate = form.paymentDate
        )
    }
}
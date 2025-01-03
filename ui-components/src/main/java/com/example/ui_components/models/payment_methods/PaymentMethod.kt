package com.example.ui_components.models.payment_methods

import com.example.ui_components.models.payment_methods.components.payment_info.PaymentInfo
import com.example.ui_components.models.payment_methods.variants.LocalPaymentMethod

sealed class PaymentMethod(val label: String) {
    data class Cash(
        val paymentInfo: PaymentInfo = PaymentInfo(),
    ) : PaymentMethod(label = "Cash") {
        companion object {
            fun mapToLocal(form: Cash) = LocalPaymentMethod.LocalCash().apply {
                paymentInfo = PaymentInfo.mapToLocal(form.paymentInfo)
            }
        }
    }


    data class Card(
        val type: String = "", /* Use the 'CardType' enum to initialize */
        val provider: String = "", /* Use the 'CardProvider' enum to initialize */
        val paymentInfo: PaymentInfo = PaymentInfo()
    ) : PaymentMethod(label = "Card") {
        companion object {
            fun mapToLocal(form: Card) = LocalPaymentMethod.LocalCard().apply {
                val fmtForm = trimmedFields(form)
                type = fmtForm.type
                provider = fmtForm.provider
                paymentInfo = PaymentInfo.mapToLocal(fmtForm.paymentInfo)
            }

            fun trimmedFields(form: Card) = form.copy(
                type = form.type.trim(),
                provider = form.provider.trim(),
                paymentInfo = form.paymentInfo
            )
        }
    }

    data class BankCheque(
        val chequeNo: String = "",
        val bank: String = "", /* Use the 'CardProvider' enum to initialize */
        val paymentInfo: PaymentInfo = PaymentInfo()
    ) : PaymentMethod(label = "Cheque") {
        companion object {

            fun mapToLocal(form: BankCheque) = LocalPaymentMethod.LocalBankCheque().apply {
                val fmtForm = trimmedFields(form)
                chequeNo = fmtForm.chequeNo
                bank = fmtForm.bank
                paymentInfo = PaymentInfo.mapToLocal(form.paymentInfo)
            }

            fun trimmedFields(form: BankCheque) = form.copy(
                chequeNo = form.chequeNo.trim(),
                bank = form.bank.trim(),
                paymentInfo = form.paymentInfo
            )
        }
    }
}
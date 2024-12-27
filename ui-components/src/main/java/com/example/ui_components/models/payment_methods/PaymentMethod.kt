package com.example.ui_components.models.payment_methods

import com.example.ui_components.exts.String.fmtDigits
import com.example.ui_components.models.payment_methods.components.PaymentCurrency
import com.example.ui_components.models.payment_methods.variants.LocalPaymentMethod

sealed class PaymentMethod(val label: String) {
    data class Cash(
        val currency: PaymentCurrency = PaymentCurrency() /* Use the 'PaymentCurrency' enum to initialize */,
        val amt: String = ""
    ) : PaymentMethod(label = "Cash") {
        companion object {
            fun mapToLocal(form: Cash) = LocalPaymentMethod.LocalCash().apply {
                val fmtForm = trimmedFields(form)
                currency = PaymentCurrency.mapToLocal(fmtForm.currency)
                amt = fmtForm.amt
            }

            fun trimmedFields(form: Cash) = form.copy(
                currency = PaymentCurrency.trimmedFields(form.currency),
                amt = form.amt.fmtDigits()
            )
        }
    }


    data class Card(
        val currency: PaymentCurrency = PaymentCurrency(),
        val type: String = "", /* Use the 'CardType' enum to initialize */
        val provider: String = "", /* Use the 'CardProvider' enum to initialize */
        val amt: String = ""
    ) : PaymentMethod(label = "Card") {
        companion object {
            fun mapToLocal(form: Card) = LocalPaymentMethod.LocalCard().apply {
                val fmtForm = trimmedFields(form)
                currency = PaymentCurrency.mapToLocal(fmtForm.currency)
                type = fmtForm.type
                provider = fmtForm.provider
                amt = fmtForm.amt
            }

            fun trimmedFields(form: Card) = form.copy(
                currency = PaymentCurrency.trimmedFields(form.currency),
                type = form.type.trim(),
                provider = form.provider.trim(),
                amt = form.amt.fmtDigits()
            )
        }
    }

    data class BankCheque(
        val currency: PaymentCurrency = PaymentCurrency(),
        val chequeNo: String = "",
        val bank: String = "", /* Use the 'CardProvider' enum to initialize */
        val amt: String = ""
    ) : PaymentMethod(label = "Cheque") {
        companion object {

            fun mapToLocal(form: BankCheque) = LocalPaymentMethod.LocalBankCheque().apply {
                val fmtForm = trimmedFields(form)
                currency = PaymentCurrency.mapToLocal(fmtForm.currency)
                chequeNo = fmtForm.chequeNo
                bank = fmtForm.bank
                amt = fmtForm.amt
            }

            fun trimmedFields(form: BankCheque) = form.copy(
                currency = PaymentCurrency.trimmedFields(form.currency),
                chequeNo = form.chequeNo.trim(),
                bank = form.bank.trim(),
                amt = form.amt.fmtDigits()
            )
        }
    }
}
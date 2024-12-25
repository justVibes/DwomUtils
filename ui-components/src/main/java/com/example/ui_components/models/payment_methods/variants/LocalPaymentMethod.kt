package com.example.ui_components.models.payment_methods.variants

import com.example.ui_components.exts.String.fmtDigits
import com.example.ui_components.models.payment_methods.PaymentMethod
import com.example.ui_components.models.payment_methods.variants.components.LocalPaymentCurrency
import io.realm.kotlin.types.EmbeddedRealmObject

sealed class LocalPaymentMethod {
    class LocalCash : EmbeddedRealmObject {
        var currency: LocalPaymentCurrency? = null
        var amt: String = ""

        companion object {
            fun mapToOriginal(form: LocalCash): PaymentMethod.Cash {
                val fmtForm = trimmedFields(form)
                return PaymentMethod.Cash(
                    currency = LocalPaymentCurrency.mapToOriginal(
                        fmtForm.currency ?: LocalPaymentCurrency()
                    ),
                    amt = fmtForm.amt
                )
            }

            fun trimmedFields(form: LocalCash) = LocalCash().apply {
                currency = form.currency?.let { LocalPaymentCurrency.trimmedFields(it) }
                amt = form.amt.fmtDigits()
            }
        }
    }

    class LocalCard : EmbeddedRealmObject {
        var currency: LocalPaymentCurrency? = null
        var type: String = ""
        var provider: String = ""
        var amt: String = ""

        companion object {
            fun mapToOriginal(form: LocalCard): PaymentMethod.Card {
                val fmtForm = trimmedFields(form)
                return PaymentMethod.Card(
                    currency = LocalPaymentCurrency.mapToOriginal(
                        fmtForm.currency ?: LocalPaymentCurrency()
                    ),
                    type = fmtForm.type,
                    provider = fmtForm.provider,
                    amt = fmtForm.amt
                )
            }

            fun trimmedFields(form: LocalCard) = LocalCard().apply {
                currency = form.currency?.let { LocalPaymentCurrency.trimmedFields(it) }
                type = form.type.trim()
                provider = form.provider.trim()
                amt = form.amt.fmtDigits()
            }
        }
    }

    class LocalBankCheque : EmbeddedRealmObject {
        var currency: LocalPaymentCurrency? = null
        var chequeNo: String = ""
        var bank: String = "" /* Use the 'CardProvider' enum to initialize */
        var amt: String = ""

        companion object {
            fun mapToOriginal(form: LocalBankCheque): PaymentMethod.BankCheque {
                val fmtForm = trimmedFields(form)
                return PaymentMethod.BankCheque(
                    currency = LocalPaymentCurrency.mapToOriginal(
                        fmtForm.currency ?: LocalPaymentCurrency()
                    ),
                    chequeNo = fmtForm.chequeNo,
                    bank = fmtForm.bank,
                    amt = fmtForm.amt
                )
            }

            fun trimmedFields(form: LocalBankCheque) = LocalBankCheque().apply {
                currency = form.currency?.let { LocalPaymentCurrency.trimmedFields(it) }
                chequeNo = form.chequeNo.fmtDigits()
                bank = form.bank.trim()
                amt = form.amt.fmtDigits()
            }
        }
    }
}
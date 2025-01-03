package com.example.ui_components.models.payment_methods.variants

import com.example.ui_components.exts.String.fmtDigits
import com.example.ui_components.models.payment_methods.PaymentMethod
import com.example.ui_components.models.payment_methods.components.payment_info.PaymentInfo
import com.example.ui_components.models.payment_methods.components.payment_info.variants.LocalPaymentInfo
import io.realm.kotlin.types.EmbeddedRealmObject

sealed class LocalPaymentMethod {
    class LocalCash : EmbeddedRealmObject {
        var paymentInfo: LocalPaymentInfo? = null

        companion object {
            fun mapToOriginal(form: LocalCash) = PaymentMethod.Cash(
                paymentInfo = form.paymentInfo?.let { LocalPaymentInfo.mapToOriginal(it) }
                    ?: PaymentInfo(),
            )
        }
    }

    class LocalCard : EmbeddedRealmObject {
        var type: String = ""
        var provider: String = ""
        var paymentInfo: LocalPaymentInfo? = null

        companion object {
            fun mapToOriginal(form: LocalCard): PaymentMethod.Card {
                val fmtForm = trimmedFields(form)
                return PaymentMethod.Card(
                    paymentInfo = fmtForm.paymentInfo?.let { LocalPaymentInfo.mapToOriginal(it) }
                        ?: PaymentInfo(),
                    type = fmtForm.type,
                    provider = fmtForm.provider,
                )
            }

            fun trimmedFields(form: LocalCard) = LocalCard().apply {
                type = form.type.trim()
                provider = form.provider.trim()
                paymentInfo = form.paymentInfo
            }
        }
    }

    class LocalBankCheque : EmbeddedRealmObject {
        var chequeNo: String = ""
        var bank: String = "" /* Use the 'CardProvider' enum to initialize */
        var paymentInfo : LocalPaymentInfo? = null

        companion object {
            fun mapToOriginal(form: LocalBankCheque): PaymentMethod.BankCheque {
                val fmtForm = trimmedFields(form)
                return PaymentMethod.BankCheque(
                    chequeNo = fmtForm.chequeNo,
                    bank = fmtForm.bank,
                    paymentInfo = form.paymentInfo?.let { LocalPaymentInfo.mapToOriginal(it) } ?: PaymentInfo()
                )
            }

            fun trimmedFields(form: LocalBankCheque) = LocalBankCheque().apply {
                chequeNo = form.chequeNo.fmtDigits()
                bank = form.bank.trim()
                paymentInfo = form.paymentInfo
            }
        }
    }
}
package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.ClientBill
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge.variants.LocalClientCharge
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment.variants.LocalClientPayment
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status.variants.LocalClientPaymentStatus
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientBill : EmbeddedRealmObject {
    var charges: RealmList<LocalClientCharge> = realmListOf()
    var totalCost: String = charges.maxOfOrNull { it.fee.toInt() }?.let { "$it" } ?: "0"
    var paymentMethod: LocalClientPayment? = null
    var paymentStatus: LocalClientPaymentStatus? = null

    companion object {
        fun mapToOriginal(form: LocalClientBill): ClientBill {
            val fmtForm = trimmedFields(form)
            return ClientBill(
                charges = fmtForm.charges.map { LocalClientCharge.mapToOriginal(it) },
                totalCost = fmtForm.totalCost,
                paymentMethod = fmtForm.paymentMethod?.let { LocalClientPayment.mapToOriginal(it) },
                paymentStatus = LocalClientPaymentStatus.mapToOriginal(fmtForm.paymentStatus!!)
            )
        }

        fun trimmedFields(form: LocalClientBill) = LocalClientBill().apply {
            charges = form.charges.map { LocalClientCharge.trimmedFields(it) }.toRealmList()
            totalCost = form.totalCost.filter { it.isDigit() }.trim()
            paymentMethod = form.paymentMethod?.let { LocalClientPayment.trimmedFields(it) }
            paymentStatus = form.paymentStatus
        }
    }
}
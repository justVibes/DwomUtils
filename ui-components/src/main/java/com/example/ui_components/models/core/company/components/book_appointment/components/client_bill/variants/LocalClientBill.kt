package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.ClientBill
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientBill : EmbeddedRealmObject {
    var description: String = ""
    var totalCost: String = ""
    var paidAmt: String = ""
    var isPaid: Boolean = paidAmt == totalCost
    var paidDate: Long = 0L
    var paymentMethod: String = ""

    companion object {
        fun mapToOriginal(form: LocalClientBill): ClientBill {
            val fmtForm = trimmedFields(form)
            return ClientBill(
                description = fmtForm.description,
                totalCost = fmtForm.totalCost,
                paidDate = fmtForm.paidDate,
                isPaid = fmtForm.isPaid,
                paidAmt = fmtForm.paidAmt,
                paymentMethod = fmtForm.paymentMethod
            )
        }

        fun trimmedFields(form: LocalClientBill) = LocalClientBill().apply {
            description = form.description.trim()
            totalCost = form.totalCost.filter { it.isDigit() }.trim()
            paidAmt = form.paidAmt.filter { it.isDigit() }.trim()
            isPaid = totalCost == paidAmt
            paidDate = form.paidDate
            paymentMethod = form.paymentMethod.trim()
        }
    }
}
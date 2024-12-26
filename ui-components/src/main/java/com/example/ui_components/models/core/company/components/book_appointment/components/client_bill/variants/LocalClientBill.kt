package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.ClientBill
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge.variants.LocalClientCharge
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment.variants.LocalClientPayment
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status.variants.LocalClientPaymentStatus
import com.example.ui_components.models.core.company.components.employee.variants.LocalEmployee
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientBill : EmbeddedRealmObject {
    var billId: String = ""
    var issueDate: Long = 0L
    var paymentHandler: LocalEmployee? = null
    var charges: RealmList<LocalClientCharge> = realmListOf()
    var totalCost: String = charges.maxOfOrNull { it.fee.toInt() }?.let { "$it" } ?: "0"
    var paymentMethod: LocalClientPayment? = null
    var paymentStatus: LocalClientPaymentStatus? = null

    companion object {
        fun mapToOriginal(form: LocalClientBill): ClientBill {
            val fmtForm = trimmedFields(form)
            return ClientBill(
                billId = fmtForm.billId,
                issueDate = fmtForm.issueDate,
                paymentHandler = fmtForm.paymentHandler?.let { LocalEmployee.mapToOriginal(it) },
                charges = fmtForm.charges.map { LocalClientCharge.mapToOriginal(it) },
                totalCost = fmtForm.totalCost,
                paymentMethod = fmtForm.paymentMethod?.let { LocalClientPayment.mapToOriginal(it) },
                paymentStatus = LocalClientPaymentStatus.mapToOriginal(fmtForm.paymentStatus!!)
            )
        }

        fun trimmedFields(form: LocalClientBill) = LocalClientBill().apply {
            billId = form.billId.trim()
            paymentHandler = form.paymentHandler?.let { LocalEmployee.trimmedFields(it) }
            issueDate = form.issueDate
            charges = form.charges.map { LocalClientCharge.trimmedFields(it) }.toRealmList()
            totalCost = form.totalCost.filter { it.isDigit() }.trim()
            paymentMethod = form.paymentMethod?.let { LocalClientPayment.trimmedFields(it) }
            paymentStatus = form.paymentStatus
        }
    }
}
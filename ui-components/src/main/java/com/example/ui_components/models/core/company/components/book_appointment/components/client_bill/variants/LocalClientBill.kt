package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants

import com.example.ui_components.exts.String.fmtDigits
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
    var paymentMethod: LocalClientPayment? = null

    var totalCost: String = ""
        get() = charges.sumOf { it.fee.toInt() }.let { "$it" }
        private set

    var paymentStatus: LocalClientPaymentStatus? = null
        get() = LocalClientPaymentStatus().apply {
            fully = paymentMethod?.let {
                (it.cash?.paymentInfo ?: it.card?.paymentInfo ?: it.cheque?.paymentInfo)?.amt == totalCost
            } ?: false
            partially = (paymentMethod?.let {
                val amount = (it.cash?.paymentInfo ?: it.card?.paymentInfo ?: it.cheque?.paymentInfo)?.amt
                amount?.let { amt ->
                    amt.fmtDigits().isNotEmpty() && amt.toInt() < totalCost.toInt()
                } == true
            } ?: false)
            none = paymentMethod == null
        }
        private set


    companion object {
        fun mapToOriginal(form: LocalClientBill): ClientBill {
            val fmtForm = trimmedFields(form)
            return ClientBill(
                billId = fmtForm.billId,
                issueDate = fmtForm.issueDate,
                paymentHandler = fmtForm.paymentHandler?.let { LocalEmployee.mapToOriginal(it) },
                charges = fmtForm.charges.map { LocalClientCharge.mapToOriginal(it) },
                paymentMethod = fmtForm.paymentMethod?.let { LocalClientPayment.mapToOriginal(it) },
            )
        }

        fun trimmedFields(form: LocalClientBill) = LocalClientBill().apply {
            billId = form.billId.trim()
            paymentHandler = form.paymentHandler?.let { LocalEmployee.trimmedFields(it) }
            issueDate = form.issueDate
            charges = form.charges.map { LocalClientCharge.trimmedFields(it) }.toRealmList()
            paymentMethod = form.paymentMethod?.let { LocalClientPayment.trimmedFields(it) }
            paymentStatus = form.paymentStatus
        }
    }
}
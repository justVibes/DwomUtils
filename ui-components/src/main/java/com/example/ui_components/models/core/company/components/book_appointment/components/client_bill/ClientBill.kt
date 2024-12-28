package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill

import com.example.ui_components.exts.String.fmtDigits
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge.ClientCharge
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment.ClientPayment
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status.ClientPaymentStatus
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants.LocalClientBill
import com.example.ui_components.models.core.company.components.employee.Employee
import io.realm.kotlin.ext.toRealmList
import java.util.UUID


data class ClientBill(
    val billId: String = "${UUID.randomUUID()}",
    val issueDate: Long = System.currentTimeMillis(),
    val paymentHandler: Employee? = null,
    val charges: List<ClientCharge> = emptyList(),
    val paymentMethod: ClientPayment? = null,
) {
    val totalCost
        get() = charges.maxOfOrNull { it.fee.fmtDigits().toInt() }?.let { "$it" } ?: "0"

    val paymentStatus: ClientPaymentStatus
        get() = ClientPaymentStatus(
            fully = paymentMethod?.let {
                (it.cash?.amt ?: it.card?.amt ?: it.cheque?.amt) == totalCost
            } ?: false,
            partially = (paymentMethod?.let {
                val amount = it.cash?.amt ?: it.card?.amt ?: it.cheque?.amt
                amount?.let { amt ->
                    amt.fmtDigits().isNotEmpty() && (amt.toInt() < totalCost.toInt())
                } == true
            } ?: false),
            none = paymentMethod == null
        )

    companion object {
        fun mapToLocal(form: ClientBill) = LocalClientBill().apply {
            val fmtForm = trimmedFields(form)
            billId = fmtForm.billId
            issueDate = fmtForm.issueDate
            paymentHandler = fmtForm.paymentHandler?.let { Employee.mapToLocal(it) }
            charges = fmtForm.charges.map { ClientCharge.mapToLocal(it) }.toRealmList()
            paymentMethod = fmtForm.paymentMethod?.let { ClientPayment.mapToLocal(it) }
        }

        fun trimmedFields(form: ClientBill) = form.copy(
            billId = form.billId.trim(),
            issueDate = form.issueDate,
            paymentHandler = form.paymentHandler?.let { Employee.trimmedFields(it) },
            charges = form.charges.map { ClientCharge.trimmedFields(it) },
            paymentMethod = form.paymentMethod?.let { ClientPayment.trimmedFields(it) },
        )
    }
}
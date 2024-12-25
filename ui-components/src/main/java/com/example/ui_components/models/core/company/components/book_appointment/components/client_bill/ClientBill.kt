package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill

import com.example.ui_components.exts.String.fmtDigits
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge.ClientCharge
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.payment.ClientPayment
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status.ClientPaymentStatus
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants.LocalClientBill
import com.example.ui_components.models.core.company.components.employee.Employee
import io.realm.kotlin.ext.toRealmList


data class ClientBill(
    val issueDate: Long = System.currentTimeMillis(),
    val paymentHandler: Employee? = null,
    val charges: List<ClientCharge> = emptyList(),
    val paymentMethod: ClientPayment? = null,
    val totalCost: String = charges.maxOfOrNull { it.fee.fmtDigits().toInt() }?.let { "$it" }
        ?: "0",
    val paymentStatus: ClientPaymentStatus = ClientPaymentStatus(
        fully = paymentMethod?.let { it.cash?.amt == totalCost || it.card?.amt == totalCost || it.cheque?.amt == totalCost }
            ?: false,
        partially = (paymentMethod?.let {
            it.cash?.amt?.let { cash ->
                cash.fmtDigits().isNotEmpty() && cash.toInt() < totalCost.toInt()
            } == true
                    || it.card?.amt?.let { card ->
                card.fmtDigits().isNotEmpty() && card.toInt() < totalCost.toInt()
            } == true
                    || it.cheque?.amt?.let { cheque ->
                cheque.fmtDigits().isNotEmpty() && cheque.toInt() < totalCost.toInt()
            } == true
        }
            ?: false),
        none = paymentMethod == null
    )
) {
    companion object {
        fun mapToLocal(form: ClientBill) = LocalClientBill().apply {
            val fmtForm = trimmedFields(form)
            issueDate = fmtForm.issueDate
            paymentHandler = fmtForm.paymentHandler?.let { Employee.mapToLocal(it) }
            charges = fmtForm.charges.map { ClientCharge.mapToLocal(it) }.toRealmList()
            totalCost = fmtForm.totalCost
            paymentMethod = fmtForm.paymentMethod?.let { ClientPayment.mapToLocal(it) }
            paymentStatus = ClientPaymentStatus.mapToLocal(fmtForm.paymentStatus)
        }

        fun trimmedFields(form: ClientBill) = form.copy(
            issueDate = form.issueDate,
            paymentHandler = form.paymentHandler?.let { Employee.trimmedFields(it) },
            charges = form.charges.map { ClientCharge.trimmedFields(it) },
            totalCost = form.totalCost.filter { it.isDigit() }.trim(),
            paymentMethod = form.paymentMethod?.let { ClientPayment.trimmedFields(it) },
            paymentStatus = form.paymentStatus
        )
    }
}
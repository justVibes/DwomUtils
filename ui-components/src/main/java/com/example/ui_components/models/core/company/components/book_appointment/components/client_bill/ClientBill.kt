package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.variants.LocalClientBill

data class ClientBill(
    val description: String = "",
    val totalCost: String = "",
    val paidAmt: String = "",
    val isPaid: Boolean = paidAmt == totalCost,
    val paidDate: Long = 0L,
    val paymentMethod: String = "", /* Use the 'PaymentMethods' enum to initialize */
) {
    companion object {
        fun mapToLocal(form: ClientBill) = LocalClientBill().apply {
            val formattedForm = trimmedFields(form)
            description = formattedForm.description
            totalCost = formattedForm.totalCost
            paidAmt = formattedForm.paidAmt
            isPaid = formattedForm.paidAmt == formattedForm.totalCost
            paidDate = formattedForm.paidDate
            paymentMethod = formattedForm.paymentMethod
        }

        fun trimmedFields(form: ClientBill) = form.copy(
            description = form.description.trim(),
            totalCost = form.totalCost.filter { it.isDigit() }.trim(),
            paidAmt = form.paidAmt.filter { it.isDigit() }.trim(),
            paidDate = form.paidDate,
            paymentMethod = form.paymentMethod.trim()
        )
    }
}
package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status.variants.LocalClientPaymentStatus

data class ClientPaymentStatus(
    val fully: Boolean = false,
    val partially: Boolean = false,
    val none: Boolean = false
) {
    companion object {
        fun mapToLocal(form: ClientPaymentStatus) = LocalClientPaymentStatus().apply {
            fully = form.fully
            partially = form.partially
            none = form.none
        }
    }
}
package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status.variants

import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.status.ClientPaymentStatus
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientPaymentStatus : EmbeddedRealmObject {
    var fully: Boolean = false
    var partially: Boolean = false
    var none: Boolean = false

    companion object {
        fun mapToOriginal(form: LocalClientPaymentStatus) = ClientPaymentStatus(
            fully = form.fully,
            partially = form.partially,
            none = form.none
        )
    }
}
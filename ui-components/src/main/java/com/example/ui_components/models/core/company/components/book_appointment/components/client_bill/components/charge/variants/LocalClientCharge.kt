package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge.variants

import com.example.ui_components.exts.String.fmtDigits
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge.ClientCharge
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientCharge : EmbeddedRealmObject {
    var description: String = ""
    var fee: String = ""

    companion object {
        fun mapToOriginal(form: LocalClientCharge): ClientCharge {
            val fmtForm = trimmedFields(form)
            return ClientCharge(
                description = fmtForm.description,
                fee = fmtForm.fee
            )
        }

        fun trimmedFields(form: LocalClientCharge) = LocalClientCharge().apply {
            description = form.description.trim()
            fee = form.fee.fmtDigits()
        }
    }
}
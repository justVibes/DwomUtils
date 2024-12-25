package com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge

import com.example.ui_components.exts.String.fmtDigits
import com.example.ui_components.models.core.company.components.book_appointment.components.client_bill.components.charge.variants.LocalClientCharge

data class ClientCharge(
    val description: String = "",
    val fee: String = ""
) {
    companion object {
        fun mapToLocal(form: ClientCharge) = LocalClientCharge().apply {
            val fmtForm = trimmedFields(form)
            description = fmtForm.description
            fee = fmtForm.fee
        }

        fun trimmedFields(form: ClientCharge) = form.copy(
            description = form.description.trim(),
            fee = form.fee.fmtDigits()
        )
    }
}
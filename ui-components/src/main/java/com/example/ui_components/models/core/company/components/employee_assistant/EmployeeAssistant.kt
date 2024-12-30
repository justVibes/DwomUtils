package com.example.ui_components.models.core.company.components.employee_assistant

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import com.example.ui_components.models.core.company.components.employee_assistant.variants.LocalEmployeeAssistant
import com.example.ui_components.models.core.company.components.employee_info.EmployeeInfo
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.toRealmList

data class EmployeeAssistant(
    val email: String = "",
    val name: Name = Name(),
    val photoUrl: String = "",
    val info: EmployeeInfo? = null,
    val isOnBreak: Boolean = false,
    @Exclude val bookedAppointments: List<BookedAppointment> = emptyList(), /*This is for local usage*/
) {
    companion object {
        fun mapToLocal(form: EmployeeAssistant) = LocalEmployeeAssistant().apply {
            val fmtForm = trimmedFields(form)
            isOnBreak = fmtForm.isOnBreak
            email = fmtForm.email
            name = Name.mapToLocal(fmtForm.name)
            photoUrl = fmtForm.photoUrl
            info = fmtForm.info?.let { EmployeeInfo.mapToLocal(it) }
            bookedAppointments =
                fmtForm.bookedAppointments.map { BookedAppointment.mapToLocal(it) }
                    .toRealmList()
        }

        fun trimmedFields(form: EmployeeAssistant) = form.copy(
            email = form.email.trim(),
            isOnBreak = form.isOnBreak,
            name = Name.trimmedFields(form.name),
            photoUrl = form.photoUrl.trim(),
            info = form.info?.let { EmployeeInfo.trimmedFields(it) },
            bookedAppointments = form.bookedAppointments.map {
                BookedAppointment.trimmedFields(
                    it
                )
            }
        )
    }
}
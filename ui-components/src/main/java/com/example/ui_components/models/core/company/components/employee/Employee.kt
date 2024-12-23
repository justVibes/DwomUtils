package com.example.ui_components.models.core.company.components.employee

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import com.example.ui_components.models.core.company.components.employee.variants.LocalEmployee
import com.example.ui_components.models.core.company.components.employee_assistant.EmployeeAssistant
import com.example.ui_components.models.core.company.components.employee_info.EmployeeInfo
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.toRealmList

data class Employee(
    val email: String = "",
    val name: Name = Name(),
    val photoUrl: String = "",
    val info: EmployeeInfo? = null,
    val assistant: EmployeeAssistant? = null,
    @Exclude val bookedAppointments: List<BookedAppointment> = emptyList(), /*This is for local usage*/
) {
    companion object {
        fun mapToLocal(form: Employee) = LocalEmployee().apply {
            val formattedForm = trimmedFields(form)
            email = formattedForm.email
            name = Name.mapToLocal(formattedForm.name)
            photoUrl = formattedForm.photoUrl
            info = formattedForm.info?.let { EmployeeInfo.mapToLocal(it) }
            assistant = formattedForm.assistant?.let { EmployeeAssistant.mapToLocal(it) }
            bookedAppointments = formattedForm.bookedAppointments
                .map { BookedAppointment.mapToLocal(it) }
                .toRealmList()
        }

        fun trimmedFields(form: Employee) = form.copy(
            email = form.email.trim(),
            name = Name.trimmedFields(form.name),
            photoUrl = form.photoUrl.trim(),
            assistant = form.assistant?.let { EmployeeAssistant.trimmedFields(it) },
            info = form.info?.let { EmployeeInfo.trimmedFields(it) },
            bookedAppointments = form.bookedAppointments.map {
                BookedAppointment.trimmedFields(it)
            }
        )
    }
}


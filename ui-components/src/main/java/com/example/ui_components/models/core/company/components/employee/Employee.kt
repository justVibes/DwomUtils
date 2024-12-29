package com.example.ui_components.models.core.company.components.employee

import com.example.ui_components.models.client.components.color.CustomColor
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
    val customColor: CustomColor = CustomColor(rand1, rand2, rand3),
    @Exclude val bookedAppointments: List<BookedAppointment> = emptyList(), /*This is for local usage*/
) {

    companion object {
        internal val rand1 = (20..235).random()
        internal val rand2 = (20..235).random()
        internal val rand3 = (20..235).random()
        fun mapToLocal(form: Employee) = LocalEmployee().apply {
            val fmtForm = trimmedFields(form)
            email = fmtForm.email
            name = Name.mapToLocal(fmtForm.name)
            photoUrl = fmtForm.photoUrl
            info = fmtForm.info?.let { EmployeeInfo.mapToLocal(it) }
            assistant = fmtForm.assistant?.let { EmployeeAssistant.mapToLocal(it) }
            bookedAppointments = fmtForm.bookedAppointments
                .map { BookedAppointment.mapToLocal(it) }
                .toRealmList()
            customColor = CustomColor.mapToLocal(fmtForm.customColor)
        }

        fun trimmedFields(form: Employee) = form.copy(
            customColor = form.customColor,
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


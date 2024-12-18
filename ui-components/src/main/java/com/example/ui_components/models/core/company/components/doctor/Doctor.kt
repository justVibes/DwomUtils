package com.example.ui_components.models.core.company.components.doctor

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import com.example.ui_components.models.core.company.components.doctor.variants.LocalDoctor
import com.example.ui_components.models.core.company.components.doctor_assistant.DoctorAssistant
import com.example.ui_components.models.core.company.components.employee_info.EmployeeInfo
import com.google.firebase.firestore.Exclude
import io.realm.kotlin.ext.toRealmList

data class Doctor(
    val email: String = "",
    val name: Name = Name(),
    val photoUrl: String = "",
    val info: EmployeeInfo? = null,
    val assistant: DoctorAssistant? = null,
    @Exclude val bookedAppointments: List<BookedAppointment> = emptyList(), /*This is for local usage*/
) {
    object Config {
        fun mapToLocal(form: Doctor) = LocalDoctor().apply {
            val formattedForm = trimmedFields(form)
            email = formattedForm.email
            name = Name.Config.mapToLocal(formattedForm.name)
            photoUrl = formattedForm.photoUrl
            info = formattedForm.info?.let { EmployeeInfo.Config.mapToLocal(it) }
            assistant = formattedForm.assistant?.let { DoctorAssistant.Config.mapToLocal(it) }
            bookedAppointments = formattedForm.bookedAppointments
                .map { BookedAppointment.Config.mapToLocal(it) }
                .toRealmList()
        }

        fun trimmedFields(form: Doctor) = form.copy(
            email = form.email.trim(),
            name = Name.Config.trimmedFields(form.name),
            photoUrl = form.photoUrl.trim(),
            info = form.info?.let { EmployeeInfo.Config.trimmedFields(it) },
            bookedAppointments = form.bookedAppointments.map {
                BookedAppointment.Config.trimmedFields(it)
            }
        )
    }
}


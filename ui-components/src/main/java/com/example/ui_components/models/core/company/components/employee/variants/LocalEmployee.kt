package com.example.ui_components.models.core.company.components.employee.variants

import com.example.ui_components.models.client.components.core.name.LocalName
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.example.ui_components.models.core.company.components.employee.Employee
import com.example.ui_components.models.core.company.components.employee.employee_info.variants.LocalEmployeeInfo
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalEmployee : EmbeddedRealmObject {
    var email: String = ""
    var name: LocalName? = null
    var photoUrl: String = ""
    var info: LocalEmployeeInfo? = null
    var bookedAppointmentsDocPaths: RealmList<String> = realmListOf()
    var bookedAppointments: RealmList<LocalBookedAppointment> = realmListOf()

    object Config {
        fun mapToOriginal(form: LocalEmployee): Employee {
            val formattedForm = trimmedFields(form)
            return Employee(
                email = formattedForm.email,
                name = LocalName.Config.mapToOriginal(formattedForm.name ?: LocalName()),
                photoUrl = formattedForm.photoUrl,
                info = formattedForm.info?.let { LocalEmployeeInfo.Config.mapToOriginal(it) },
                bookedAppointmentsDocPaths = formattedForm.bookedAppointmentsDocPaths,
                bookedAppointments = formattedForm.bookedAppointments.map {
                    LocalBookedAppointment.Config.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalEmployee) = LocalEmployee().apply {
            email = form.email.trim()
            name = form.name?.let { LocalName.Config.trimmedFields(it) }
            photoUrl = form.photoUrl.trim()
            info = form.info?.let { LocalEmployeeInfo.Config.trimmedFields(it) }
            bookedAppointmentsDocPaths =
                form.bookedAppointmentsDocPaths.map { it.trim() }.toRealmList()
            bookedAppointments =
                form.bookedAppointments.map { LocalBookedAppointment.Config.trimmedFields(it) }
                    .toRealmList()

        }
    }
}
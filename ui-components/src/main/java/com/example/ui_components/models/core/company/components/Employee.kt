package com.example.ui_components.models.core.company.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class Employee(
    var email: String = "",
    var name: String = "",
    var photoUrl: String = "",
    var info: EmployeeInfo? = null,
    var bookedAppointments: List<DocumentReference> = emptyList(),
    @Exclude var tempBookedAppointments: List<BookedAppointment> = emptyList(), /*This is for local usage*/
) {
    object MapToStripped {
        fun from(form: Employee) =
            EmployeeStripped(
                email = form.email,
                name = form.name,
                photoUrl = form.photoUrl,
            )
    }
}

@Entity
data class EmployeeStripped(
    @PrimaryKey
    var email: String = "",
    var name: String = "",
    var photoUrl: String = "",
    var companyId: String = "",
) {
    object MapToOriginal {
        fun from(form: EmployeeStripped, bookedAppointmentsRef: List<DocumentReference>, tempBookedAppointments: List<BookedAppointment>) =
            Employee(
                email = form.email,
                name = form.name,
                photoUrl = form.photoUrl,
                bookedAppointments = bookedAppointmentsRef,
                tempBookedAppointments = tempBookedAppointments
            )
    }
}


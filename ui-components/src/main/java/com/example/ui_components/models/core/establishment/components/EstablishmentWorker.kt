package com.example.ui_components.models.core.establishment.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentReference


data class EstablishmentWorker(
    var email: String = "",
    var name: String = "",
    var photoUrl: String = "",
    var establishmentId: String = "",
    var bookedAppointments: List<DocumentReference> = emptyList(),
    var tempBookedAppointments: List<BookedAppointments> = emptyList(), /*This is for local usage*/

) {
    object MapToStripped {
        fun from(form: EstablishmentWorker) =
            EstablishmentWorkerStripped(
                email = form.email,
                name = form.name,
                photoUrl = form.photoUrl,
                establishmentId = form.establishmentId,
            )
    }
}

@Entity
data class EstablishmentWorkerStripped(
    @PrimaryKey
    var email: String = "",
    var name: String = "",
    var photoUrl: String = "",
    var establishmentId: String = "",
) {
    object MapToOriginal {
        fun from(form: EstablishmentWorkerStripped, bookedAppointmentsRef: List<DocumentReference>, tempBookedAppointments: List<BookedAppointments>) =
            EstablishmentWorker(
                email = form.email,
                name = form.name,
                photoUrl = form.photoUrl,
                establishmentId = form.establishmentId,
                bookedAppointments = bookedAppointmentsRef,
                tempBookedAppointments = tempBookedAppointments
            )
    }
}


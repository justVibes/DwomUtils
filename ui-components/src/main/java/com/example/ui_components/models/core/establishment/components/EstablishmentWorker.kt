package com.example.ui_components.models.core.establishment.components

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentReference


@Entity
data class EstablishmentWorker(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val name: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val establishmentId: String = "",
    @Ignore val bookedAppointments: List<DocumentReference>
) {
    constructor(
        _id: Int = 0,
        name: String = "",
        photoUrl: String = "",
        email: String = "",
        establishmentId: String = "",
    ) : this(
        _id = 0,
        name = "",
        photoUrl = "",
        email = "",
        establishmentId = "",
        bookedAppointments = emptyList()
    )
}


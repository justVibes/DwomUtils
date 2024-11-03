package com.example.ui_components.models.core.establishment.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Transient


@Entity
data class EstablishmentWorker(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val name: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val establishmentId: String = "",
    @Transient val bookedAppointments: List<DocumentReference> = emptyList()
)


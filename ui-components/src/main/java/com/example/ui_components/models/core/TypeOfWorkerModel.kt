package com.example.ui_components.models.core

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Transient

@Entity
data class TypeOfWorkerModel(
    @PrimaryKey
    var formattedWorkerTitle: String = "",
    var abvWorkerTitle: String = "",
    var endProduct: String = "",
    var typeOfAppointment: String = "",
    var needsEstablishment: Boolean = false,
    var estCategory: String = "",
    @Transient var establishments: List<DocumentReference> = emptyList()
)

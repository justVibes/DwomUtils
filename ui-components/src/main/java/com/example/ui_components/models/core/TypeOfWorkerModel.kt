package com.example.ui_components.models.core

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentReference

@Entity
data class TypeOfWorkerModel(
    @PrimaryKey
    var formattedWorkerTitle: String = "",
    var abvWorkerTitle: String = "",
    var endProduct: String = "",
    var typeOfAppointment: String = "",
    var needsEstablishment: Boolean = false,
    var estCategory: String = "",
    @Ignore var establishments: List<DocumentReference> = emptyList()
) {
    constructor(
        formattedWorkerTitle: String = "",
        abvWorkerTitle: String = "",
        endProduct: String = "",
        typeOfAppointment: String = "",
        needsEstablishment: Boolean = false,
        estCategory: String = "",
    ) : this(
        formattedWorkerTitle = "",
        abvWorkerTitle = "",
        endProduct = "",
        typeOfAppointment = "",
        needsEstablishment = false,
        estCategory = "",
        establishments = emptyList()
    )
}

package com.example.ui_components.models.core.type_of_worker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypeOfWorkerModelLocal(
    @PrimaryKey
    var formattedWorkerTitle: String = "",
    var endProduct: String = "",
    var typeOfAppointment: String = "",
    var needsEstablishment: Boolean = false,
    var typeOfEstablishment: String = "",
    var abvWorkerTitle: String = "",
)
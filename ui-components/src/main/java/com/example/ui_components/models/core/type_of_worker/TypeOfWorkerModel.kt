package com.example.ui_components.models.core.type_of_worker

data class TypeOfWorkerModel(
    var formattedWorkerTitle: String = "",
    var endProduct: String = "",
    val establishments: List<Establishment> = emptyList(),
    val typeOfAppointment: String = "",
    val needsEstablishment: Boolean = false
)

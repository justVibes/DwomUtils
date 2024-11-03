package com.example.ui_components.models.core.type_of_worker

data class TypeOfWorkerModelLocal(
    var formattedWorkerTitle: String = "",
    var endProduct: String = "",
    var typeOfAppointment: String = "",
    var needsEstablishment: Boolean = false,
    var typeOfEstablishment: String = ""
)
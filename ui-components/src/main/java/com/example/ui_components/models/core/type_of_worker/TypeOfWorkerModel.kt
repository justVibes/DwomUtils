package com.example.data_models_dwom.core.type_of_worker

data class TypeOfWorkerModel(
    var formattedWorkerTitle: String = "",
    var endProduct: String = "",
    val establishments: List<Establishment> = emptyList(),
    val typeOfAppointment: String = ""
)

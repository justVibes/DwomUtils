package com.example.data_models_dwom.gigrequest

import com.example.data_models_dwom.core.type_of_worker.TypeOfWorkerModel
import java.util.UUID

data class RequestInfo(
    var selectedImages: List<RequestImage> = emptyList(),
    val selectedRequestOptions: List<RequestOption> = emptyList(),
    val creationDate: String = "",
    val requestId: String = UUID.randomUUID().toString(),
    var appointmentDate: String = "",
    var appointmentTime: String = "",
    val minBudget: String = "",
    val maxBudget: String = "",
    val typeOfWorker: TypeOfWorkerModel = TypeOfWorkerModel(),
    val staticPrice: String = "",
)

data class RequestOption(
    val title: String = "",
    val option: String = ""
)

data class RequestImage(
    val title: String = "",
    val image: String = ""
)
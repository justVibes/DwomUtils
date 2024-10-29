package com.example.data_models_dwom.core

import com.example.data_models_dwom.core.type_of_worker.TypeOfWorkerModel
import com.example.data_models_dwom.core.type_of_worker.TypeOfWorkerModelLocal
import com.example.data_models_dwom.gigrequest.GigRequest

data class UserModel(
    val userContentLoading: Boolean = false,
    var isWorker: Boolean = false,
    var tagName: String = "User#1234",
    var email: String = "",
    var photoUrl: String = "",
    var phoneNumber: String? = null,
    var uid: String = "",
    var typeOfWorker: TypeOfWorkerModelLocal? = null,
    var acceptedRequests: List<GigRequest> = emptyList() /*This is for local usage.*/
)
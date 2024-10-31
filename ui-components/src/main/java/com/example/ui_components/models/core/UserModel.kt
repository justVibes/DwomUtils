package com.example.ui_components.models.core

import com.example.ui_components.models.core.type_of_worker.TypeOfWorkerModelLocal
import com.example.ui_components.models.gigrequest.GigRequest

data class UserModel(
    val userContentLoading: Boolean = false,
    var isWorker: Boolean = false,
    var tagName: String = "User#1234",
    var email: String = "",
    var photoUrl: String = "",
    var phoneNumber: String? = null,
    var uid: String = "",
    var typeOfWorker: TypeOfWorkerModelLocal? = null,
    var connectionKeys: List<ConnectionKey> = emptyList(),
    var requests: List<GigRequest> = emptyList() /*This is for local usage.*/
)

data class ConnectionKey(
    val userId: String = "",
    val establishmentConnectionId: String = ""
)
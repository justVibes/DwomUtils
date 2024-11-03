package com.example.ui_components.models.core.user

import com.example.ui_components.models.core.TypeOfWorkerModel
import com.example.ui_components.models.core.user.components.ConnectionKey
import com.example.ui_components.models.gigrequest.GigRequest
import kotlinx.serialization.Serializable

data class UserModel(
    val userContentLoading: Boolean = false,
    var isWorker: Boolean = false,
    var tagName: String = "User#1234",
    var email: String = "",
    var photoUrl: String = "",
    var phoneNumber: String? = null,
    var uid: String = "",
    var typeOfWorker: TypeOfWorkerModel? = null,
    var connectionKeys: List<ConnectionKey> = emptyList(),
    var requests: List<GigRequest> = emptyList() /*This is for local usage.*/
)



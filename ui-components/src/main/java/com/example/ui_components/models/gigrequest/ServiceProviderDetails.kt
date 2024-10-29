package com.example.data_models_dwom.gigrequest

data class ServiceProviderDetails(
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    val tagName: String = "",
    val email: String = "",
    val uid: String = "",
    val pfp: String = "",
)
package com.example.ui_components.models.gigrequest

data class NonEstServiceProviderDetails(
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    val tagName: String = "",
    val email: String = "",
    val uid: String = "",
    val pfp: String = "",
)
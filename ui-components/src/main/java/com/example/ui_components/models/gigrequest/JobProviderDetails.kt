package com.example.ui_components.models.gigrequest

data class JobProviderDetails(
    val accountType: String = "Regular", /*TODO create an enum for the different tiers*/
    val uid: String = "",
    val tagName: String = "",
    val pfp: String = "",
)

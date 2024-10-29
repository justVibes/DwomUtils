package com.example.data_models_dwom.gigrequest

data class RequesterDetails(
    val accountType: String = "Regular", /*TODO create an enum for the different tiers*/
    val uid: String = "",
    val tagName: String = "",
    val pfp: String = "",
)

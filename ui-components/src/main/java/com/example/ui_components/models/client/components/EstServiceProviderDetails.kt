package com.example.ui_components.models.client.components

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class EstServiceProviderDetails(
    val uid: String = "${UUID.randomUUID()}",
    val name: String = "",
    val photoUrl: String = ""
)
package com.example.ui_components.models.client.components

import kotlinx.serialization.Serializable

@Serializable
data class ServiceProvider(
    val uid: String = "",
    val name: String = ""
)
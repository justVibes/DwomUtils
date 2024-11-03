package com.example.ui_components.models.core.user.components


data class ConnectionKey(
    val userId: String = "",
    val establishmentConnectionId: String = "",
    val config: ConnectionKeyConfig = ConnectionKeyConfig()
)
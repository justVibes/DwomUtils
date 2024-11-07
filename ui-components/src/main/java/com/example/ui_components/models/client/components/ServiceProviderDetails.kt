package com.example.ui_components.models.client.components

import com.example.ui_components.models.core.user.components.PortfolioFile
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class ServiceProviderDetails(
    val uid: String = "${UUID.randomUUID()}",
    val name: String = "",
    val photoUrl: String = "",
    val rating: Int = 0,
    @Transient val portfolio: List<PortfolioFile> = emptyList()
)
package com.example.ui_components.models.client.components

import com.example.ui_components.models.core.user.components.PortfolioFile
import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class ServiceProviderDetails(
    val uid: String = "${UUID.randomUUID()}",
    val name: String = "",
    val photoUrl: String = "",
    val rating: Int = 0,
    @Transient val portfolioRef: List<DocumentReference> = emptyList(),
    @Transient val tempPortfolio: List<PortfolioFile> = emptyList() /*This is for local usage.*/
)
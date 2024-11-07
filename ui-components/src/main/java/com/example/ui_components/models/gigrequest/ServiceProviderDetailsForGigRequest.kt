package com.example.ui_components.models.gigrequest

import com.example.ui_components.models.core.user.components.PortfolioFile
import com.google.firebase.firestore.DocumentReference

data class ServiceProviderDetailsForGigRequest(
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    val tagName: String = "",
    val email: String = "",
    val uid: String = "",
    val pfp: String = "",
    val rating: Int = 0,
    val portfolioRef: List<DocumentReference> = emptyList(),
    val tempPortfolio: List<PortfolioFile> = emptyList() /*This is for local usage.*/
)
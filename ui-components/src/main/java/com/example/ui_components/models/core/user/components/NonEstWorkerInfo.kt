package com.example.ui_components.models.core.user.components

data class NonEstWorkerInfo(
    var uid: String = "",
    var photoUrl: String = "",
    var tagName: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var rating: Int = 0,
    var formattedWorkerTitle: String = "",
    var typeOfAppointment: String = "",
    var endProduct: String = "",
    var portfolio: List<PortfolioFile> = emptyList()
)
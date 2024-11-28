package com.example.ui_components.models.core.freelancer

import com.example.ui_components.models.core.freelancer.components.PortfolioFile

data class Freelancer(
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
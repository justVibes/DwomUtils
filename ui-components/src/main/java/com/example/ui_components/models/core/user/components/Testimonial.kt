package com.example.ui_components.models.core.user.components

import com.example.ui_components.models.client.components.ServiceProviderDetails

data class Testimonial(
    val witnessName: String = "",
    val testimony: String = "",
    val witnessDate: String = "",
    val serviceProviderDetails: ServiceProviderDetails = ServiceProviderDetails()
)
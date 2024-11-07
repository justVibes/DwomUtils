package com.example.ui_components.models.gigrequest

import com.example.ui_components.models.client.components.ServiceProviderDetails

data class GigRequest(
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    var requestInfo: RequestInfo = RequestInfo(),
    val requesterDetails: RequesterDetails = RequesterDetails(),
    var selectedServiceProvider: ServiceProviderDetailsForGigRequest = ServiceProviderDetailsForGigRequest(),
    var bidingServiceProviders: List<ServiceProviderDetails> = emptyList(),
    var rescheduleAppointmentForm: RescheduleAppointmentForm = RescheduleAppointmentForm(),
)
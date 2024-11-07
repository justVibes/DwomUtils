package com.example.ui_components.models.gigrequest

data class GigRequest(
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    var requestInfo: RequestInfo = RequestInfo(),
    val requesterDetails: RequesterDetails = RequesterDetails(),
    var selectedServiceProvider: ServiceProviderDetailsForGigRequest = ServiceProviderDetailsForGigRequest(),
    var bidingServiceProviders: List<ServiceProviderDetailsForGigRequest> = emptyList(),
    var rescheduleAppointmentForm: RescheduleAppointmentForm = RescheduleAppointmentForm(),
)
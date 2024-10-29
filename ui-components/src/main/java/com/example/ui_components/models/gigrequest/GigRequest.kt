package com.example.data_models_dwom.gigrequest

data class GigRequest(
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    var requestInfo: RequestInfo = RequestInfo(),
    val requesterDetails: RequesterDetails = RequesterDetails(),
    var serviceProviderDetails: ServiceProviderDetails = ServiceProviderDetails(), /*TODO this is for searches*/
    var rescheduleAppointmentForm: RescheduleAppointmentForm = RescheduleAppointmentForm(),
)
package com.example.ui_components.models.gigrequest

data class GigRequest(
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    var requestInfo: RequestInfo = RequestInfo(),
    val requesterDetails: RequesterDetails = RequesterDetails(),
    var nonEstServiceProviderDetails: NonEstServiceProviderDetails = NonEstServiceProviderDetails(), /*TODO this is for searches*/
    var rescheduleAppointmentForm: RescheduleAppointmentForm = RescheduleAppointmentForm(),
)
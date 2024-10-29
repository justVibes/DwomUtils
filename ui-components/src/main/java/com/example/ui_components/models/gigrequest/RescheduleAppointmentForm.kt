package com.example.data_models_dwom.gigrequest

data class RescheduleAppointmentForm(
    var rescheduleMessage: String = "",
    val fromDate: String = "",
    val fromTime: String = "",
    val toDate: String = "",
    val toTime: String = "",
    val setByRequester: Boolean = false,
    var status: String = ReScheduleStatus.PENDING.name,
)

enum class ReScheduleStatus {
    ACCEPTED, PENDING, DENIED
}
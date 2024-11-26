package com.example.ui_components.models.gigrequest

data class RescheduleJobForm(
    val fromDate: String = "",
    val fromTime: String = "",
    val toDate: String = "",
    val toTime: String = "",
    var rescheduleMessage: String = "",
    val setByJobProvider: Boolean = false,
    var status: String = "", /* Use the 'ReScheduleStatus' enum to initialize */
)

enum class ReScheduleStatus {
    ACCEPTED, DENIED
}
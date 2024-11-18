package com.example.ui_components.ui.core.core_logic.conversion

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeConversion {
    fun hhMMAFormat(time: Long): String {
        val timeFormatter = SimpleDateFormat("HH:mm a", Locale.getDefault())
        var formattedTime = timeFormatter.format(Date(time))
        formattedTime.take(2).toInt().let {
            if (it > 12) formattedTime = (it - 12).toString() + formattedTime.drop(2)
            if (it == 0) formattedTime = "12${formattedTime.drop(2)}"
        }
        return if (time == 0L) "" else formattedTime
    }

    fun mmmDDYYYFormat(date: Long, addAnExtraDay: Boolean = true): String {
        val updDate = Date(date + if (addAnExtraDay) 86400000L else 0L)
        val formattedDate =
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(updDate)
        return if (date == 0L) "" else formattedDate
    }

    fun mmIddIyy(date: Long, addAnExtraDay: Boolean = false): String {
        val updDate = Date(date + (if (addAnExtraDay) 86400000L else 0L))
        val formattedDate =
            SimpleDateFormat("MM/dd/yy", Locale.getDefault()).format(updDate)
        return if (date == 0L) "" else formattedDate
    }
}
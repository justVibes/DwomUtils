package com.example.ui_components.core.conversion

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeConversion {
    fun hhMMAFormat(time: Long): String{
        val timeFormatter = SimpleDateFormat("HH:mm a", Locale.getDefault())
        var formattedTime = timeFormatter.format(Date(time))
        formattedTime.take(2).toInt().let {
            if (it > 12) formattedTime = (it - 12).toString() + formattedTime.drop(2)
            if (it == 0) formattedTime = "12${formattedTime.drop(2)}"
        }
        return if(time == 0L) "" else formattedTime
    }

    fun mmmDDYYYFormat(date: Long): String{
        val updDate = Date(date + 86400000L)
        val formattedDate =
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(updDate)
        return if(date == 0L) "" else formattedDate
    }
}
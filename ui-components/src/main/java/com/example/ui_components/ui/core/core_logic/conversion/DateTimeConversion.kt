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

    fun mmmDDYYYFormat(date: Long, addAnExtraDay: Boolean = false): String {
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

    fun lifeSpan(date: Long): String {
        val dayPeriod = 86400000L/1000L
        val weekPeriod = (dayPeriod * 7)
        val monthPeriod = (weekPeriod * 4)
        return when (val lifespan = (System.currentTimeMillis() - date)/1000) {
            in (0L until 60L) -> "$lifespan secs"
            in (60L until 3600L) -> "${lifespan / 60L} min" + if ((lifespan / 60L) > 1) "s" else ""
            in (3600L until dayPeriod) -> "${lifespan / 3600L} hr" + if ((lifespan / 3600L) > 1) "s" else ""
            in (dayPeriod until weekPeriod) -> "${lifespan / dayPeriod} day" + if ((lifespan / dayPeriod) > 1) "s" else ""
            in (weekPeriod until monthPeriod) -> "${lifespan / weekPeriod} week" + if ((lifespan / weekPeriod) > 1) "s" else ""
            in (monthPeriod until (monthPeriod * 12)) -> "${lifespan / monthPeriod} month" + if ((lifespan / monthPeriod) > 1) "s" else ""
            else -> "${lifespan / (monthPeriod * 12)} yr" + if ((lifespan / (monthPeriod * 12)) > 1) "s" else ""
        } + " ago"
    }
}
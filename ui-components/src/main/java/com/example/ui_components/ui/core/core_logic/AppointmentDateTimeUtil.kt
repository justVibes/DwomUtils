package com.example.ui_components.ui.core.core_logic

import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object AppointmentDateTimeUtil {
    fun daysUntilAppointment(appointmentDate: String): String {
        val format = "MMM dd, yyyy HH:mm a"
        val dateTimeFormatter =
            DateTimeFormatter
                .ofPattern(format.dropLast(2), Locale.getDefault())
        val currentDate = dateTimeFormatter.format(LocalDateTime.now())

        val formattedAppointmentDate = convertToArmyTime(appointmentDate)
//        val formattedCurrentDate = convertToArmyTime(currentDate)


        val toDate = LocalDateTime.parse(formattedAppointmentDate, dateTimeFormatter)
        val fromDate = LocalDateTime.parse(currentDate, dateTimeFormatter)

        var period: String

        try {
            Duration.between(fromDate, toDate).let {
                if (it.seconds < 1L) throw Exception()
                period = if (it.toDays() < 1L) {
                    when {
                        it.toMinutes() == 0L -> "${it.seconds}s"
                        it.toMinutes() in 1..59 -> "${it.toMinutes()}m"
                        else -> "${it.toHours()}h"
                    }

                } else {
                    when {
                        it.toDays() / 7L > 0L -> "${it.toDays() / 7L}w"
                        else -> "${it.toDays()}d"
                    }

                } + " left"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            period = "Expired"
        }

        return period
    }

    fun requestLifespan(creationDate: String): String {
        val offsetDateTime = OffsetDateTime.parse(creationDate)
        return Duration.between(offsetDateTime, OffsetDateTime.now()).let {
            if (it.toDays() < 1L) {
                when {
                    it.toMinutes() == 0L -> "${it.seconds}s"
                    it.toMinutes() in 1..59 -> "${it.toMinutes()}m"
                    else -> "${it.toHours()}h"
                }

            } else {
                when {
                    it.toDays() / 7L > 0L -> "${it.toDays() / 7L}w"
                    else -> "${it.toDays()}d"
                }

            }
        } + " ago"
    }


    private fun convertToArmyTime(date: String): String {
        /*
        *The date must adhere to the format: (MMM dd, yyyy HH:mm a)
        * e.g: (Oct 30, 2024 01:00 PM)
        */
        return date.let {
            if (it.takeLast(2) == "AM") {
                it.dropLast(3)
            } else {
                it.dropLast(3)
                    .dropLastWhile { char -> char != ' ' } +
                        "${
                            (date.takeWhile { char -> char != ':' }
                                .takeLast(2)
                                .toInt() + 12)
                        }:${date.dropLast(3).takeLast(2)}"
            }
        }
    }
}
package com.example.ui_components.exts

import kotlin.String

object String {
    fun String.fmtDigits() = this.filter { it.isDigit() }.trim()
    fun String.fmtMins(maxTime: Int, skipMaxCheck: Boolean = false): String {
        val time = this.toIntOrNull() ?: return "[ERROR]"
        return when {
            time >= maxTime && !skipMaxCheck -> "$maxTime".fmtMins(maxTime, true)
            (0 until 60).contains(time) -> "$time mins"
            time == 60 -> "1 hr"
            (60 until 1440).contains(time) -> (time / 60).let { "$it hr${if (it > 1) "s" else ""} ${time - (60 * it)} mins" }
            (1440 until 10080).contains(time) -> "${time / 1440} dys"
            else -> "[ERROR]"
        }
    }
}
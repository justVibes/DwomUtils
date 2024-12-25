package com.example.ui_components.exts

import kotlin.String

object String {
    fun String.fmtDigits() = this.filter { it.isDigit() }.trim()
}
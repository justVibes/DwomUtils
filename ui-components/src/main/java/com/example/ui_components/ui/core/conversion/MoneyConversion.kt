package com.example.ui_components.ui.core.conversion

object MoneyConversion {
    fun kFormat(amount: String): String {
        val strippedFormatAmt = amount.filter { it != ',' && it != ' ' }
        return if (strippedFormatAmt.length < 4) amount
        else if (strippedFormatAmt.length <= 6) strippedFormatAmt.dropLast(3) + "K"
        else strippedFormatAmt.dropLast(6) + "m"
    }

    fun commaFormat(amount: String): String {
        return if (amount.isNotEmpty()) {
            amount.reversed()
                .windowed(3, step = 3, partialWindows = true)
                .joinToString(",")
                .reversed()
        }else {
            ""
        }
    }
}
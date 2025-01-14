package com.example.ui_components.models.color

import androidx.annotation.IntRange
import com.example.ui_components.models.color.variants.LocalCustomColor

data class CustomColor(
    @IntRange(0L, 255L) val red: Int = 0,
    @IntRange(0L, 255L) val green: Int = 0,
    @IntRange(0L, 255L) val blue: Int = 0
){
    companion object {
        fun mapToLocal(form: CustomColor) = LocalCustomColor().apply {
            red = form.red
            green = form.green
            blue = form.blue
        }
    }
}
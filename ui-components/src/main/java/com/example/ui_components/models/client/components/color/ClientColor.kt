package com.example.ui_components.models.client.components.color

import androidx.annotation.IntRange
import com.example.ui_components.models.client.components.color.variants.LocalClientColor

data class ClientColor(
    @IntRange(0L, 255L) val red: Int = 0,
    @IntRange(0L, 255L) val green: Int = 0,
    @IntRange(0L, 255L) val blue: Int = 0
){
    companion object {
        fun mapToLocal(form: ClientColor) = LocalClientColor().apply {
            red = form.red
            green = form.green
            blue = form.blue
        }
    }
}
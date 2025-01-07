package com.example.ui_components.models.color.variants

import com.example.ui_components.models.color.CustomColor
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalCustomColor: EmbeddedRealmObject {
    var red: Int = 0
    var green: Int = 0
    var blue: Int = 0
    
    companion object {
        fun mapToOriginal(form: LocalCustomColor) = CustomColor(
            red = form.red,
            green = form.green,
            blue = form.blue
        )
    }
}
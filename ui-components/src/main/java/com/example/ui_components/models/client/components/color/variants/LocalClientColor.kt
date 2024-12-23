package com.example.ui_components.models.client.components.color.variants

import com.example.ui_components.models.client.components.color.ClientColor
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientColor: EmbeddedRealmObject {
    var red: Int = 0
    var green: Int = 0
    var blue: Int = 0
    
    companion object {
        fun mapToOriginal(form: LocalClientColor) = ClientColor(
            red = form.red,
            green = form.green,
            blue = form.blue
        )
    }
}
package com.example.ui_components.models.client.components.components

import androidx.compose.ui.graphics.Color
import com.example.ui_components.theme.ConfirmBlue
import com.example.ui_components.theme.Delete

enum class EditType(val color: Color){
     REMOVED(Delete), MODIFIED(ConfirmBlue), NONE(Color.Transparent)
}
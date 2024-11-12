package com.example.ui_components.models.client.components.components

import androidx.compose.ui.graphics.Color
import com.example.ui_components.theme.ConfirmBlue

enum class EditType(val color: Color){
     MODIFIED(ConfirmBlue.copy(alpha = .3f)), NONE(Color.Transparent)
}
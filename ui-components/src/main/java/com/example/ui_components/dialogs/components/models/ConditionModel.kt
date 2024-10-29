package com.example.ui_components.dialogs.components.models

import androidx.compose.ui.graphics.Color


data class ConditionModel(
    val condition: Boolean,
    val color: Color,
    val confirmText: String,
    val processingText: String,
    val mainHeader: String,
    val subHeader: String = ""
)
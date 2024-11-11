package com.example.ui_components.models.client.components.components

fun stringComparisonEditType(original: String, modified: String): EditType {
    return when {
        original == modified -> EditType.NONE
        else -> EditType.MODIFIED
    }
}
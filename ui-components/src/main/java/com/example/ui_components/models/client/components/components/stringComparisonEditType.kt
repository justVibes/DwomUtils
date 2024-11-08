package com.example.ui_components.models.client.components.components

fun stringComparisonEditType(original: String, modified: String): EditType {
    return when {
        original == modified -> EditType.NONE
        original.contains(modified) -> EditType.MODIFIED
        else -> EditType.REMOVED
    }
}
package com.example.ui_components.models.client.components.core

fun stringComparisonEditType(original: String, modified: String): EditType {
    return when {
        original == modified -> EditType.NONE
        else -> EditType.MODIFIED
    }
}
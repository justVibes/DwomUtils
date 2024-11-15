package com.example.ui_components.models.core


sealed class TaskResult {
    data class SUCCESS(val data: Any? = null, val message: String = "") : TaskResult()
    data class FAIL(val message: String) : TaskResult()
}
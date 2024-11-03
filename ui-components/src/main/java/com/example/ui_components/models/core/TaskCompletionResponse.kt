package com.example.ui_components.models.core

sealed class TaskCompletionResponse {
    data class SUCCESS(val data: Any? = null, val message: String) : TaskCompletionResponse()
    data class FAIL(val message: String): TaskCompletionResponse()
    object PENDING: TaskCompletionResponse()
}
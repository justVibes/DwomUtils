package com.example.ui_components.models.core.task

sealed class TaskCompletionResponse {
    data class SUCCESS(val data: Any) : TaskCompletionResponse()
    data class FAIL(val message: String): TaskCompletionResponse()
    object PENDING: TaskCompletionResponse()
}
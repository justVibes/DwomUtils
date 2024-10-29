package com.example.data_models_dwom.core.task

sealed class TaskCompletionResponse {
    data class SUCCESS(val data: Any) : TaskCompletionResponse()
    data class FAIL(val message: String): TaskCompletionResponse()
    object PENDING: TaskCompletionResponse()
}
package com.example.ui_components.models.core

data class SearchHint<T>(
    val hint: String,
    val timeStamp: Long = System.currentTimeMillis(),
    val obj: T? = null,
    val type: SearchHintType
)

enum class SearchHintType{
    Clients, Employee
}
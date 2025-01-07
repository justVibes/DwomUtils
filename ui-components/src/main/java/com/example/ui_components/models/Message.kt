package com.example.ui_components.models

import com.example.ui_components.models.core.company.components.employee.Employee
import java.util.Calendar

data class Message(
    val sender: Employee? = null,
    val receiver: Employee? = null,
    val text: String = "",
//    val client: ClientItemCopy? = null,
    val timeStamp: Long = Calendar.getInstance().timeInMillis,
    val collectionPath: String? = null
) {
    fun isMyMessage(myEmail: String) = sender?.publicInfo?.email == myEmail
}
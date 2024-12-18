package com.example.ui_components.models

import com.example.ui_components.models.client.ClientItemCopy
import com.example.ui_components.models.core.company.components.doctor.Doctor
import java.util.Calendar

data class Message(
    val sender: Doctor? = null,
    val receiver: Doctor? = null,
    val text: String = "",
    val client: ClientItemCopy? = null,
    val timeStamp: Long = Calendar.getInstance().timeInMillis,
    val collectionPath: String? = null
) {
    fun isMyMessage(myEmail: String) = sender?.email == myEmail
}
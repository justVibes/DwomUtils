package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Calendar
import java.util.UUID

@Serializable
@Entity
data class ClientNote(
    @PrimaryKey
    val noteId: String = "${UUID.randomUUID()}",
    var clientId: String = "",
    val creationDate: Long = Calendar.getInstance().timeInMillis,
    val authorizedEditors: String = "",
    val author: String = "",
    val title: String = "",
    val note: String = "",
)

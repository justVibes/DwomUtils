package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ClientVitals(
    @PrimaryKey
    var clientId: String = "",
    val heartRate: String = "",
    val respiratoryRate: String = "",
    val bloodPressure: String = "",
    val bloodOxygen: String = "",
    val bodyTemperatureCel: String = "",
)
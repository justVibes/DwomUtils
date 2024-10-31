package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class EmergencyContactInfo(
    @PrimaryKey
    var clientId: String = "",
    var name: String = "",
    var phoneNumber: String = "",
    var presentAddress: String = "",
)
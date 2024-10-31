package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ClientInfo(
    @PrimaryKey
    var clientId: String = "",
    var tagName: String = "",
    var photoUrl: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var sex: String = "",
    val birthDate: String = "",
    var birthPlace: String = "",
    var height: String = "",
    var weight: String = "",
    var presentAddress: String = "",
    var occupation: String = "",
    var age: Int = 0, /*Generated based on the given birth date*/
    var localPhoneNumber: String = "",
    var emailAddress: String = "",
)
package com.example.ui_components.models.client.components.info

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClientInfoStripped(
    @PrimaryKey
    var clientId: String = "",
    var tagName: String = "",
    var photo: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var sex: String = "", /*Use 'ValidGenders' enum to initialize*/
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
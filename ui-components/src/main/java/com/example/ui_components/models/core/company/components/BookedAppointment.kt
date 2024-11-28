package com.example.ui_components.models.core.company.components

import androidx.room.Entity
import com.example.ui_components.models.client.ClientItem
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class BookedAppointment(
    val date: String = "",
    val time: String = "",
    val approxDurationInMins: Int = 0,
    val employeeRef: DocumentReference,
    val clientRef: DocumentReference,
    @Exclude val tempEmployee: Employee = Employee(), /*This is for local usage*/
    @Exclude val tempClient: ClientItem = ClientItem()/*This is for local usage*/
)


@Entity(primaryKeys = ["date", "time"])
data class BookedAppointmentStripped(
    val date: String = "",
    val time: String = "",
    val approxDurationInMins: Int = 0,
)
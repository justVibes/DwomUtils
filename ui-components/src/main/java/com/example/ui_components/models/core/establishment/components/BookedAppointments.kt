package com.example.ui_components.models.core.establishment.components

import androidx.room.Entity
import com.example.ui_components.models.client.ClientItem
import com.google.firebase.firestore.DocumentReference

data class BookedAppointments(
    val _id: Int = 0,
    val date: String = "",
    val time: String = "",
    val approxDurationInMins: Int = 0,
    val workerRef: DocumentReference,
    val clientRef: DocumentReference,
    val tempWorker: EstablishmentWorker = EstablishmentWorker(), /*This is for local usage*/
    val tempClient: ClientItem = ClientItem()/*This is for local usage*/
)


@Entity(primaryKeys = ["date", "time"])
data class BookedAppointmentsStripped(
    val date: String = "",
    val time: String = "",
    val approxDurationInMins: Int = 0,
)
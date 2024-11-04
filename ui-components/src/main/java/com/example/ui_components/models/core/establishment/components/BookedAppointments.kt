package com.example.ui_components.models.core.establishment.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.ClientItem
import com.google.android.gms.common.api.Api.Client
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

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
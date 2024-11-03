package com.example.ui_components.models.core.establishment.components

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookedAppointments(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val date: String = "",
    val time: String = "",
    val approxDurationInMins: Int = 0
)
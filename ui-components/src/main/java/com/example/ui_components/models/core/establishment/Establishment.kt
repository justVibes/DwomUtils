package com.example.ui_components.models.core.establishment

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.ui_components.models.core.establishment.components.EstablishmentWorker
import com.google.firebase.firestore.DocumentReference
import java.util.UUID

@Entity
data class Establishment(
    @PrimaryKey
    val establishmentId: String = "${UUID.randomUUID()}",
    val photoUrl: String = "",
    val name: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val category: String = "",
    @Ignore
    var workers: List<DocumentReference> = emptyList(),
    @Ignore
    var tempWorkers: List<EstablishmentWorker>  = emptyList()/*This is for local usage*/
) {
    constructor(
        establishmentId: String = "${UUID.randomUUID()}",
        photoUrl: String = "",
        name: String = "",
        coarseLocation: String = "",
        type: String = "",
        category: String = "",
    ) : this(
        establishmentId = "${UUID.randomUUID()}",
        photoUrl = "",
        name = "",
        coarseLocation = "",
        type = "",
        category = "",
        workers = emptyList(),
        tempWorkers = emptyList()
    )
}

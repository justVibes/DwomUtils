package com.example.ui_components.models.core.establishment

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.core.establishment.components.EstablishmentWorker
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import java.util.UUID

data class Establishment(
    val establishmentId: String = "${UUID.randomUUID()}",
    val photoUrl: String = "",
    val name: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val category: String = "",
    val collectionReference: CollectionReference? = null,
    var workers: List<DocumentReference> = emptyList(),
    var tempWorkers: List<EstablishmentWorker> = emptyList()/*This is for local usage*/
) {
    object MapToStripped {
        fun from(form: Establishment) =
            EstablishmentStripped(
                establishmentId = form.establishmentId,
                photoUrl = form.photoUrl,
                name = form.name,
                coarseLocation = form.coarseLocation,
                type = form.type,
                category = form.category,
            )
    }
}

@Entity
data class EstablishmentStripped(
    @PrimaryKey
    val establishmentId: String = "${UUID.randomUUID()}",
    val photoUrl: String = "",
    val name: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val category: String = "",
) {
    object MapToOriginal {
        fun from(
            form: EstablishmentStripped,
            collectionReference: CollectionReference,
            workers: List<DocumentReference>,
            tempWorkers: List<EstablishmentWorker>
        ) =
            Establishment(
                establishmentId = form.establishmentId,
                photoUrl = form.photoUrl,
                name = form.name,
                coarseLocation = form.coarseLocation,
                type = form.type,
                category = form.category,
                workers = workers,
                tempWorkers = tempWorkers,
                collectionReference = collectionReference
            )
    }
}

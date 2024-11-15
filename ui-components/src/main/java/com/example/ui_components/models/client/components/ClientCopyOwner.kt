package com.example.ui_components.models.client.components

import com.example.ui_components.models.core.establishment.components.EstablishmentWorker
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class ClientCopyOwner (
    /*
    * The reference to the owner of this copy.
    * NB: The owner of the copy must be apart of the same establishment as the service provider who
    *   created the client.
    */
    val ownerDocRef: DocumentReference? = null,

    /* The location of the copy of the client's data (tailored just for the owner of this copy) */
    val clientCopyDocRef: DocumentReference? = null,

    /*
    * This is for local usage.
    * It's used to visually represent the worker that owns this copy, for the benefit of the service provider
    * that created the client (the owner).
    */
    @Exclude var tempOwner: EstablishmentWorker? = null
)
package com.example.ui_components.models.client.components

import com.google.firebase.firestore.DocumentReference

data class ClientCopyOwner (
    val ownerInfoDocRef: DocumentReference? = null,
    val clientCopyDocRef: DocumentReference? = null
)
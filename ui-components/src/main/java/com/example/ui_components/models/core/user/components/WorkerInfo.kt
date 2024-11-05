package com.example.ui_components.models.core.user.components

import com.google.firebase.firestore.CollectionReference

data class WorkerInfo(
    var formattedWorkerTitle: String,
    var establishmentRef: CollectionReference,
)
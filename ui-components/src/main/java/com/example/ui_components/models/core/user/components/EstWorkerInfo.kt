package com.example.ui_components.models.core.user.components

import com.google.firebase.firestore.DocumentReference

data class EstWorkerInfo(
    var formattedWorkerTitle: String = "",
    var establishmentPath: String = "",
    var estName: String = "",
    var abvWorkerTitle: String = "",
    var estCategory: String = "",
    var workerDocRef: DocumentReference? = null
)
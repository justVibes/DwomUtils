package com.example.ui_components.models.client.components.info.components

import com.google.firebase.firestore.Exclude

data class ClientPhoto(
    val url: String = "",
    val storagePath: String = "",
    /* This is only to be initialized if the client's photo 'url' is being replaced/updated */
    @Exclude val updatedUrl: String = ""
)
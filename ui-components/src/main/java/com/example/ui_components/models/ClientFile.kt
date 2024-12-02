package com.example.ui_components.models

import com.example.ui_components.models.client.ClientItem

data class ClientFile(
    /* Use the names from the 'ClientDetailsDestinations' enum class to initialize */
    val accessibleSections: Set<String> = emptySet(),
    val client: ClientItem = ClientItem()
)
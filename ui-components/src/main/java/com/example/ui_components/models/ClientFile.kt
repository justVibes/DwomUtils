package com.example.ui_components.models

import com.example.ui_components.models.client.ClientItem

data class ClientFile(
    /* Use the names from the 'ClientDetailsDestinations' enum class to initialize */
    val accessibleSections: List<String> = listOf(),
    val client: ClientItem = ClientItem()
)
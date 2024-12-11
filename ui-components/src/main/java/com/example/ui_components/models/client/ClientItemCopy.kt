package com.example.ui_components.models.client

data class ClientItemCopy(
    /* Use the names from the 'ClientDetailsDestinations' enum class to initialize */
    val accessibleSections: List<String> = listOf(),
    val client: ClientItem = ClientItem()
)
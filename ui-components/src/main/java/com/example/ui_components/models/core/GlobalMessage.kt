package com.example.ui_components.models.core

data class GlobalMessage(
    val creationDate: String = "",
    val title: String = "",
    val message: String = "",
    /*
    * This should contain a list of the emails that should see this message.
    * If 'targets' is empty and 'isVisible' is true then the global message can be seen by everyone*/
    val targets: List<String> = emptyList(),
    val isPublic: Boolean = false,
    val isCloseable: Boolean = false
)

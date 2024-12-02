package com.example.ui_components.models

import com.example.ui_components.models.core.company.components.Employee

data class ConversationMembers(
    val member1: Employee? = null,
    val member2: Employee? = null,
    val lastMsg: Message? = null,
)
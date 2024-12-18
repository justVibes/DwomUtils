package com.example.ui_components.models

import com.example.ui_components.models.core.company.components.doctor.Doctor

data class ConversationMembers(
    val member1: Doctor? = null,
    val member2: Doctor? = null,
    val lastMsg: Message? = null,
)
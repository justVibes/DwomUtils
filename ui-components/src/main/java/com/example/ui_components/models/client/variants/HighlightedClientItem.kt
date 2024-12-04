package com.example.ui_components.models.client.variants

import com.example.ui_components.models.client.components.emergency_contact_info.variants.HighlightedEmergencyContactInfo
import com.example.ui_components.models.client.components.info.variants.HighlightedClientInfo
import com.example.ui_components.models.client.components.note.variants.HighlightedClientNote
import com.example.ui_components.models.client.components.vitals.variants.HighlightedClientVitals

data class HighlightedClientItem(
    val clientInfo: HighlightedClientInfo = HighlightedClientInfo(),
    val vitals: HighlightedClientVitals = HighlightedClientVitals(),
    val emergencyContactInfo: HighlightedEmergencyContactInfo = HighlightedEmergencyContactInfo(),
    val notes: List<HighlightedClientNote> = emptyList()
)
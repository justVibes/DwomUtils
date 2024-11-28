package com.example.ui_components.models.core.freelancer.components

import com.google.firebase.firestore.DocumentReference

data class Testimonial(
    val witnessName: String = "",
    val testimony: String = "",
    val witnessDate: String = "",
    val serviceProviderSummaryRef: DocumentReference? = null
)
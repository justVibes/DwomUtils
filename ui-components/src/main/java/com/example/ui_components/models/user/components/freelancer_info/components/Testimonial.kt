package com.example.ui_components.models.user.components.freelancer_info.components

import com.google.firebase.firestore.DocumentReference

data class Testimonial(
    val witnessName: String = "",
    val testimony: String = "",
    val witnessDate: String = "",
    val serviceProviderSummaryRef: DocumentReference? = null
)
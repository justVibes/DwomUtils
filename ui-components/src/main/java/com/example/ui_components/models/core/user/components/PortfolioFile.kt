package com.example.ui_components.models.core.user.components

import com.example.ui_components.models.core.user.components.Testimonial
import java.util.Calendar

data class PortfolioFile(
    val testimonial: Testimonial = Testimonial(),
    val referencePictures: List<String> = emptyList(),
    val creationDate: String = "${Calendar.getInstance().timeInMillis}"
)

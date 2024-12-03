package com.example.ui_components.models.client.components.service_provider

import kotlinx.serialization.Serializable

@Serializable
data class ServiceProvider(
    val uid: String = "",
    val name: String = ""
) {
    object Config {
        fun mapToLocal(form: ServiceProvider) = LocalServiceProvider().apply {
            val formattedForm = trimmedFields(form)
            uid = formattedForm.uid
            name = formattedForm.name
        }

        fun trimmedFields(form: ServiceProvider) = form.copy(
            uid = form.uid.trim(),
            name = form.name.trim()
        )
    }
}
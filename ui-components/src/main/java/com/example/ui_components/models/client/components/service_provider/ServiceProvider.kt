package com.example.ui_components.models.client.components.service_provider

import com.example.ui_components.models.client.components.core.name.Name
import kotlinx.serialization.Serializable


@Serializable
data class ServiceProvider(
    val uid: String = "",
    val abvTitle: String = "",
    val name: Name = Name(),
    val email: String = "",
    val photoUrl: String = ""
) {
    companion object {
        fun mapToLocal(form: ServiceProvider) = LocalServiceProvider().apply {
            val formattedForm = trimmedFields(form)
            uid = formattedForm.uid
            name = Name.mapToLocal(formattedForm.name)
            email = formattedForm.email
            photoUrl = formattedForm.photoUrl
            abvTitle = formattedForm.abvTitle

        }

        fun trimmedFields(form: ServiceProvider) = form.copy(
            uid = form.uid.trim(),
            name = Name.trimmedFields(form.name),
            email = form.email.trim(),
            photoUrl = form.photoUrl.trim(),
            abvTitle = form.abvTitle.trim()
        )
    }
}
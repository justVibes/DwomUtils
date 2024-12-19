package com.example.ui_components.models.client.components.summary

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.client.components.summary.variants.LocalClientSummary

data class ClientSummary(
    val uid: String = "",
    val photoUrl: String = "",
    val name: Name = Name(),
    val email: String = "",
    val docPath: String? = null
) {
    object Config {
        fun mapToLocal(form: ClientSummary) = LocalClientSummary().apply {
            val formattedForm = trimmedFields(form)
            uid = formattedForm.uid
            photoUrl = formattedForm.photoUrl
            name = Name.Config.mapToLocal(formattedForm.name)
            email = formattedForm.email
            docPath = formattedForm.docPath
        }

        fun trimmedFields(form: ClientSummary) = form.copy(
            uid = form.uid.trim(),
            photoUrl = form.photoUrl.trim(),
            name = Name.Config.trimmedFields(form.name),
            email = form.email.trim(),
            docPath = form.docPath?.trim()
        )
    }
}
package com.example.ui_components.models.user.components.core.summary

import com.example.ui_components.models.core.name.Name
import com.example.ui_components.models.user.components.core.summary.variants.LocalUserSummary

data class UserSummary(
    val uid: String = "",
    val photoUrl: String = "",
    val name: Name = Name(),
    val email: String = "",
    val docPath: String? = null
) {
    companion object {
        fun mapToLocal(form: UserSummary) = LocalUserSummary().apply {
            val fmtForm = trimmedFields(form)
            uid = fmtForm.uid
            photoUrl = fmtForm.photoUrl
            name = Name.mapToLocal(fmtForm.name)
            email = fmtForm.email
            docPath = fmtForm.docPath
        }

        fun trimmedFields(form: UserSummary) = form.copy(
            uid = form.uid.trim(),
            photoUrl = form.photoUrl.trim(),
            name = Name.trimmedFields(form.name),
            email = form.email.trim(),
            docPath = form.docPath?.trim()
        )
    }
}
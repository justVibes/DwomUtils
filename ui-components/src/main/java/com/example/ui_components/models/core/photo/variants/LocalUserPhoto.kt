package com.example.ui_components.models.core.photo.variants

import com.example.ui_components.models.core.photo.UserPhoto
import io.realm.kotlin.types.EmbeddedRealmObject


class LocalUserPhoto : EmbeddedRealmObject {
    var url: String = ""
    var storagePath: String? = null
    var newUrl: String = ""
    var isDeleted: Boolean = false

    companion object {
        fun mapToOriginal(form: LocalUserPhoto): UserPhoto {
            val fmtForm = trimmedFields(form)
            return UserPhoto(
                url = fmtForm.url,
                storagePath = fmtForm.storagePath,
                newUrl = fmtForm.newUrl,
                isDeleted = fmtForm.isDeleted
            )
        }

        fun trimmedFields(form: LocalUserPhoto) = LocalUserPhoto().apply {
            url = form.url.trim()
            storagePath = form.storagePath?.trim()
            newUrl = form.newUrl.trim()
            isDeleted = form.isDeleted
        }
    }
}
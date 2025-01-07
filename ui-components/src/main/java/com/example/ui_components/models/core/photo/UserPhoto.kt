package com.example.ui_components.models.core.photo

import com.example.ui_components.models.core.photo.variants.LocalUserPhoto
import com.google.firebase.firestore.Exclude

data class UserPhoto(
    val url: String = "",
    val storagePath: String? = null,
    /* This is only to be initialized if the client's photo 'url' is being replaced/updated */
    @Exclude val newUrl: String = "",
     val isDeleted: Boolean = false
){
    companion object{
        fun mapToLocal(form: UserPhoto) = LocalUserPhoto().apply {
            val fmtForm = trimmedFields(form)
            url = fmtForm.url
            storagePath = fmtForm.storagePath
            newUrl = fmtForm.newUrl
            isDeleted = fmtForm.isDeleted
        }

        fun trimmedFields(form: UserPhoto) = form.copy(
            url = form.url.trim(),
            storagePath = form.storagePath?.trim(),
            newUrl = form.newUrl.trim(),
            isDeleted = form.isDeleted
        )
    }
}
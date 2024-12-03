package com.example.ui_components.models.client.components.lab_result.variants

import com.example.ui_components.models.client.components.lab_result.LabResult
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmInstant

class LocalLabResult : EmbeddedRealmObject {
    var resultType: String = ""
    var resultUrl: String = ""
    var storagePath: String? = null
    var isDeleted: Boolean = false
    var creationDateTime: Long = RealmInstant.now().epochSeconds
    var authorName: String = ""
    var title: String = ""

    object Config {
        fun mapToOriginal(form: LocalLabResult) = LabResult(
            resultType = form.resultType,
            resultUrl = form.resultUrl,
            storagePath = form.storagePath,
            isDeleted = form.isDeleted,
            creationDateTime = form.creationDateTime,
            authorName = form.authorName
        )
    }
}
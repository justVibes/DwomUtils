package com.example.ui_components.models.client.components.info.components

import io.realm.kotlin.types.EmbeddedRealmObject


class LocalClientPhoto : EmbeddedRealmObject {
    var url: String = ""
    var storagePath: String = ""

    /* This is only to be initialized if the client's photo 'url' is being replaced/updated */
    var updatedUrl: String = ""
}
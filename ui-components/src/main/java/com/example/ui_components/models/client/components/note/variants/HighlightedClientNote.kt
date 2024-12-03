package com.example.ui_components.models.client.components.note.variants

import com.example.ui_components.models.client.components.core.EditType

data class HighlightedClientNote(
    var isNew: EditType = EditType.NONE,
    var author: EditType = EditType.NONE,
    var title: EditType = EditType.NONE,
    var note: EditType = EditType.NONE,
    var indexOfChangedTitleWordsList: List<Int> = emptyList(),
    var indexOfChangedNoteWordsList: List<Int> = emptyList()
)
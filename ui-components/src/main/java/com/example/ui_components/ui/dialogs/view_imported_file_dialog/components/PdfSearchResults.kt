package com.example.ui_components.ui.dialogs.view_imported_file_dialog.components

import android.graphics.RectF

data class PdfSearchResults(
    val page: Int,
    val results: List<RectF>
)
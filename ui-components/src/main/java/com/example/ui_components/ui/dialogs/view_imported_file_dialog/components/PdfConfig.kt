package com.example.ui_components.ui.dialogs.view_imported_file_dialog.components

import android.graphics.Bitmap
import com.example.ui_components.file_modifiers.PdfBitmapConverter

data class PdfConfig (
    val searchText: String = "",
    val pdfBitmapConverter: PdfBitmapConverter,
    val renderedPages: List<Bitmap>,
    val searchResults: List<PdfSearchResults>,
    val onSearchTextUpdated: (String) -> Unit,
    val onSearchResultsUpdated: (List<PdfSearchResults>) -> Unit,
    val onClearClicked: () -> Unit
)

package com.example.ui_components.ui.dialogs.import_alert_dialog.components

import androidx.annotation.DrawableRes
import com.example.ui_components.R

sealed class ImportDocumentTypes(val fileExtension: String, @DrawableRes val icon: Int, val name: String) {
    object PDF: ImportDocumentTypes(".pdf", R.drawable.ic_pdf, "PDF")
//    object WORD: ImportDocumentTypes(".doc/.docx", R.drawable.ic_word, "WORD")
    object PHOTO: ImportDocumentTypes(".png/.jpg", R.drawable.ic_photos, "PHOTO")
}
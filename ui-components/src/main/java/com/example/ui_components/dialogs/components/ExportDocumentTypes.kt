package com.example.ui_components.dialogs.components

import androidx.annotation.DrawableRes
import com.example.ui_components.R

sealed class ExportDocumentTypes(val fileExtension: String, @DrawableRes val icon: Int) {
    object PDF: ExportDocumentTypes(".pdf", R.drawable.ic_pdf)
    object WORD: ExportDocumentTypes(".doc", R.drawable.ic_word)
    object EXCEL: ExportDocumentTypes(".csv", R.drawable.ic_excel)
}
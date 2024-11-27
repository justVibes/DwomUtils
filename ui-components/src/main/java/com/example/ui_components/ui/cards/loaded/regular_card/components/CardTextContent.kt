package com.example.ui_components.ui.cards.loaded.regular_card.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

data class CardTextContent(
    val text: String = "",
    val annotatedText: AnnotatedString? = null,
    val separator: Char = ' ',
    val isHeader: Boolean = false,
    val style: @Composable () -> TextStyle = {
        if (isHeader){
            MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }else{
            MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.outline
            )
        }
    },
    val separateText: Boolean = false,
    val maxLines: Int = 1,
    val overflow: TextOverflow = TextOverflow.Ellipsis
)








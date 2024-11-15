package com.example.ui_components.ui.core.core_logic

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


object CustomColor{
    @Composable
    fun cardFadedGray() =  if (isSystemInDarkTheme()) Color.DarkGray else MaterialTheme.colorScheme.outlineVariant
    @Composable
    fun cardFadedGraySelected() =  MaterialTheme.colorScheme.onSurface.copy(alpha = .8f)
    @Composable
    fun photoFadedGray() =  MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .5f)
    @Composable
    fun photoFadedGrayBorder() =  if(isSystemInDarkTheme()) MaterialTheme.colorScheme.outlineVariant else MaterialTheme.colorScheme.outline
    @Composable
    fun errorFadedGray() =  MaterialTheme.colorScheme.onSurface.copy(alpha = .25f)
    @Composable
    fun photoBorderFadedGray() =  MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .7f)
    @Composable
    fun detailsContainer() = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .1f)
}

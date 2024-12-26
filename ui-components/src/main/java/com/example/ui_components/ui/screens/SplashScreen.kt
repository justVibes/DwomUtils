package com.example.ui_components.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.theme.Teal
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(appName: String = "Book It") {
    var isLinearLoadingVisible by remember {
        mutableStateOf(false)
    }
    var textIndex by remember {
        mutableIntStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        while (textIndex < appName.length) {
            if (textIndex == 0) {
                delay(300)
            }
            textToDisplay += appName[textIndex]
            textIndex++
            delay(150)
            if (textIndex == appName.length) {
                delay(1500)
                isLinearLoadingVisible = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textToDisplay,
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Spacer(Modifier.height(10.dp))
        AnimatedVisibility(
            visible = isLinearLoadingVisible,
            enter = fadeIn(tween(durationMillis = 500))
        ) {
            LinearProgressIndicator(
                modifier = Modifier.width(150.dp),
                color = Teal,
                trackColor = if(isSystemInDarkTheme())MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outlineVariant,
                gapSize = 1.dp
            )
        }
    }
}

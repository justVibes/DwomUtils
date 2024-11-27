package com.example.ui_components.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_components.R

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    subject: String = "",
    message: String = "",
    action: String = "",
    @DrawableRes defaultPhoto: Int = R.drawable.ic_search_document,
    isPeronSearch: Boolean = false,
    isNoRequests: Boolean = false
) {
    val iconSize = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 200.dp
        WindowWidthSizeClass.Medium -> 220.dp
        WindowWidthSizeClass.Expanded -> 260.dp
        else -> 60.dp
    }
    val textSize = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 25.sp
        WindowWidthSizeClass.Medium -> 30.sp
        WindowWidthSizeClass.Expanded -> 35.sp
        else -> 16.sp
    }

    val photo = when {
        isPeronSearch -> R.drawable.ic_person_search
        isNoRequests -> R.drawable.ic_sad_face
        else -> defaultPhoto
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter = painterResource(photo),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = message.ifEmpty { "No ${subject.first().uppercase()}${subject.drop(1).lowercase()} Found" },
            fontSize = textSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
            textAlign = TextAlign.Center
        )
        Text(
            text = action,
            fontSize = textSize*.5,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .25f),
            textAlign = TextAlign.Center
        )
    }
}
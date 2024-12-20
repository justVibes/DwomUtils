package com.example.ui_components.ui.backdrops

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BlurredBackdrop(@DrawableRes img: Int) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .blur(25.dp),
        painter = painterResource(img),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}
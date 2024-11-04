package com.example.ui_components.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_components.R

@Composable
fun SplashScreen(
    appName: String = "Book It",
    @DrawableRes appIcon: Int = R.drawable.app_bg_outline
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(120.dp)
                .clip(MaterialTheme.shapes.medium),
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.Black.copy(
                    alpha = .85f
                ),
            ),
            elevation = CardDefaults.elevatedCardElevation(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .clip(CircleShape),
                painter = painterResource(appIcon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onSurfaceVariant,
                    blendMode = BlendMode.SrcIn
                )
            )
        }
        Spacer(Modifier.height(5.dp))
        Text(
            text = appName,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

package com.example.ui_components.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.models.core.GlobalMessage
import com.example.ui_components.ui.core.core_ui.CloseBtn

@Composable
fun GlobalMessageScreen(
    modifier: Modifier = Modifier,
    globalMessage: GlobalMessage,
    onCloseClicked: () -> Unit
) {
    val contentPadding = 10.dp
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .blur(25.dp),
            painter = painterResource(R.drawable.ic_compact_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        if (globalMessage.isCloseable) {
            CloseBtn(modifier = Modifier.padding(contentPadding)) {
                onCloseClicked()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = contentPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = globalMessage.title,
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Text(
                text = globalMessage.message,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = "Powered by Book It",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
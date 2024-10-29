package com.example.ui_components.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_components.core.CustomColor
import com.example.ui_components.cards.RegularCard
import com.example.ui_components.theme.ConfirmBlue

@Composable
fun <T> ListAlertDialog(
    modifier: Modifier = Modifier,
    mainHeader: String,
    mainHeaderStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
    subHeader: String = "",
    subHeaderStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
    isProcessing: Boolean,
    listContent: List<T>,
    confirmButtonEnabled: List<Boolean> = emptyList(),
    confirmText: String = "",
    processingText: String = "",
    itemCard: @Composable() ((T) -> Unit),
    onConfirmClicked: () -> Unit,
    onHideDialog: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onHideDialog() },
        title = {
            HeaderSection(
                isProcessing,
                onHideDialog,
                mainHeader,
                mainHeaderStyle,
                subHeader,
                subHeaderStyle
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onConfirmClicked() },
                enabled = !isProcessing && !confirmButtonEnabled.contains(false),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ConfirmBlue,
                    contentColor = Color.White
                )
            ) {
                if (!isProcessing) {
                    Text(confirmText)
                } else {
                    Text("$processingText...", fontStyle = FontStyle.Italic)
                    Spacer(Modifier.width(14.dp))
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                listContent.forEach { item ->
                    itemCard(item)
                }
            }
        }
    )
}

@Composable
private fun HeaderSection(
    isProcessing: Boolean,
    onHideDialog: () -> Unit,
    mainHeader: String,
    mainHeaderStyle: TextStyle,
    subHeader: String,
    subHeaderStyle: TextStyle
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(ConfirmBlue)
            )
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = .05f))
                    .clickable(enabled = !isProcessing) {
                        onHideDialog()
                    }
                    .padding(5.dp),
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = mainHeader,
            style = mainHeaderStyle
        )
        Text(
            text = subHeader,
            style = subHeaderStyle
        )
    }
}
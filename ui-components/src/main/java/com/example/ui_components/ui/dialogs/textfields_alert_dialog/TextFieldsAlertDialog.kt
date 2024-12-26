package com.example.ui_components.ui.dialogs.textfields_alert_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.dialogs.textfields_alert_dialog.components.ConditionModel
import com.example.ui_components.ui.dialogs.textfields_alert_dialog.components.FieldModel
import com.example.ui_components.ui.theme.ConfirmBlue

@Composable
fun TextFieldsAlertDialog(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    conditionModel: ConditionModel? = null,
    mainColor: Color = ConfirmBlue,
    mainHeader: String = "",
    subHeader: String = "",
    confirmText: String = "",
    processingText: String = "",
    fields: List<FieldModel>,
    isProcessing: Boolean,
    onFieldUpdated: (FieldModel) -> Unit,
    onConfirmClicked: () -> Unit,
    onHideDialog: () -> Unit
) {
    val dialogSize = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1f to .7f
        WindowWidthSizeClass.Medium -> 1f to .8f
        WindowWidthSizeClass.Expanded -> 1f to .8f
        else -> .5f to .5f
    }

    AlertDialog(
        modifier = modifier
            .fillMaxWidth(dialogSize.first)
            .fillMaxHeight(dialogSize.second),
        onDismissRequest = {},
        title = {
            HeaderSection(
                conditionModel,
                mainColor,
                isProcessing,
                onHideDialog,
                mainHeader,
                windowSizeClass,
                subHeader
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                fields.forEach { field ->
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                        value = field.text,
                        onValueChange = { text ->
                            onFieldUpdated(
                                FieldModel(
                                    identifier = field.identifier,
                                    text = text
                                )
                            )
                        },
                        label = {
                            Text(
                                text = field.identifier,
                                style = MaterialTheme.typography.titleSmall
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedLabelColor = MaterialTheme.colorScheme.outline,
                            unfocusedLabelColor = MaterialTheme.colorScheme.outline.copy(
                                alpha = .7f
                            )
                        ),
                        singleLine = field.isSingleLine
                    )
                }
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onConfirmClicked() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = conditionModel?.color ?: mainColor
                ),
                enabled = !isProcessing
            ) {
                if (!isProcessing) {
                    Text(
                        text = conditionModel?.confirmText ?: confirmText
                    )
                } else {
                    Text(
                        text = conditionModel?.processingText ?: processingText,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(Modifier.width(15.dp))
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        color = Color.White
                    )
                }
            }
        }
    )
}

@Composable
private fun HeaderSection(
    conditionModel: ConditionModel?,
    mainColor: Color,
    isProcessing: Boolean,
    onHideDialog: () -> Unit,
    mainHeader: String,
    windowSizeClass: WindowSizeClass,
    subHeader: String
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(conditionModel?.color ?: mainColor)
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
            text = conditionModel?.mainHeader ?: mainHeader,
            style = MaterialTheme.typography.let {
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> it.titleLarge
                    WindowWidthSizeClass.Medium -> it.headlineMedium
                    WindowWidthSizeClass.Expanded -> it.headlineLarge
                    else -> it.titleSmall
                }
            },
            fontWeight = FontWeight.Bold
        )
        if (conditionModel?.subHeader?.isNotEmpty() ?: subHeader.isNotEmpty()) {
            Text(
                text = conditionModel?.subHeader ?: subHeader,
                style = MaterialTheme.typography.let {
                    when (windowSizeClass.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> it.titleMedium
                        WindowWidthSizeClass.Medium -> it.headlineSmall
                        WindowWidthSizeClass.Expanded -> it.headlineMedium
                        else -> it.titleSmall
                    }
                },
                fontWeight = FontWeight.Bold
            )
        }
    }
}





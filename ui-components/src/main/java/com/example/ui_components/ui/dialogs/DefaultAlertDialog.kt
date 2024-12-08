package com.example.ui_components.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun DefaultAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    action: String = "",
    mainActionBtn: ActionBtnDefaults,
    subActionBtn: ActionBtnDefaults = ActionBtnDefaults("Cancel") {},
    isProcessing: Boolean = false,
    onHideDialog: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { if (!isProcessing) onHideDialog() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(2) { idx ->
                    val btn = when (idx) {
                        0 -> mainActionBtn
                        1 -> subActionBtn
                        else -> ActionBtnDefaults(text = "[ERROR]") { Red }
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { btn.onClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (idx == 0) btn.color.invoke() else Transparent,
                            contentColor = if (idx == 0) White else btn.color.invoke()
                        ),
                        shape = CircleShape,
                        enabled = !isProcessing
                    ) {
                        Text(
                            text = btn.text,
                            fontWeight = if (idx == 0) FontWeight.Bold else FontWeight.Normal
                        )
                        if (isProcessing) {
                            Spacer(Modifier.width(5.dp))
                            CircularProgressIndicator(
                                color = White,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
                }
            }
        },
        title = {
            Text(text = title, fontWeight = FontWeight.Bold)
        },
        text = {
            Text(
                text = buildAnnotatedString {
                    append(desc)
                    if (action.isNotEmpty()) {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" $action")
                        }
                    }
                }
            )
        }
    )
}


data class ActionBtnDefaults(
    val text: String,
    val color: @Composable () -> Color = { MaterialTheme.colorScheme.outline },
    val onClick: () -> Unit
)
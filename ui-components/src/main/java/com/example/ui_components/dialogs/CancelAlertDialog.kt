package com.example.ui_components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui_components.core.TextStyling
import javax.security.auth.Subject

@Composable
fun CancelAlertDialog(
    modifier: Modifier = Modifier,
    subject: String,
    cancellationMessage: String = "",
    onUpdateCancellationMessage: (String) -> Unit,
    onCancelClicked: () -> Unit,
    onHideDialog: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onHideDialog() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                repeat(2) { index ->
                    val btn: Pair<Pair<String, Color>, Pair<Color, Float>> = when (index) {
                        0 -> ("Cancel $subject" to Color.Red) to (Color.White to .4f)
                        1 -> ("Close" to Color.Transparent) to (MaterialTheme.colorScheme.onSurface.copy(
                            alpha = .5f
                        ) to .25f)

                        else -> ("{47:35}" to Color.Unspecified) to (Color.Unspecified to 1f)
                    }

                    Button(
                        modifier = Modifier.wrapContentWidth(),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = .5f
                            )
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = btn.first.second,
                            contentColor = btn.second.first
                        ),
                        onClick = {
                            when (index) {
                                0 -> onCancelClicked()
                                1 -> onHideDialog()
                            }
                        }
                    ) {
                        Text(text = btn.first.first)
                    }
                    if (index != 1) {
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        },
        title = {
            Text(text = "Cancel $subject")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Are you sure you want to cancel your ${subject.lowercase()}?",
                )

                OutlinedTextField(
                    value = cancellationMessage,
                    onValueChange = {onUpdateCancellationMessage(it)},
                    placeholder = {
                        TextStyling.ColorDifference(
                            text = "Cancellation message (optional)",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleSmall,
                            separator = '('
                        )
                    },
                    minLines = 3
                )
            }
        }
    )
}
package com.example.ui_components.ui.dialogs.delete_alert_dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.ui_components.theme.CancelRed
import com.example.ui_components.ui.dialogs.delete_alert_dialog.components.DeletionType

@Composable
fun DeleteAlertDialog(
    deletionType: DeletionType,
    deletionSubject: String,
    subjectGender: String = "",
    isDeleting: Boolean,
    onDeleteClick: () -> Unit,
    onHideDialog: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            if (!isDeleting) {
                onHideDialog()
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(2) { index ->
                    val btn: Pair<Pair<String, Color>, Pair<Color, Float>> = when (index) {
                        0 -> ("Delete" to CancelRed) to (Color.White to .4f)
                        1 -> ("Cancel" to Color.Transparent) to (MaterialTheme.colorScheme.onSurface.copy(
                            alpha = .5f
                        ) to .25f)

                        else -> ("{47:35}" to Color.Unspecified) to (Color.Unspecified to 1f)
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (btn.first.second == Color.Transparent) btn.second.first else Color.Transparent
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = btn.first.second,
                            contentColor = btn.second.first
                        ),
                        onClick = {
                            when (index) {
                                0 -> onDeleteClick()
                                1 -> onHideDialog()
                            }
                        },
                        enabled = !isDeleting
                    ) {
                        if (index != 0) {
                            Text(
                                text = btn.first.first,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }

                        if (index == 0 && isDeleting) {
                            Text(
                                text = "Deleting...",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontStyle = FontStyle.Italic
                                )
                            )

                            Spacer(Modifier.width(15.dp))
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp)
                            )
                        } else if (index == 0) {
                            Text(
                                text = btn.first.first,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }
                    }
                    if (index != 1) {
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        },
        title = {
            Text(text = "Delete ${deletionType.name}", fontWeight = FontWeight.Bold)
        },
        text = {
            Row {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = buildAnnotatedString {
                        append("Are you sure you want to delete ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(deletionSubject)
                        }
                        if (subjectGender.isNotEmpty()) {
                            append(" and all $subjectGender data")
                        }
                        append("? ")
                        append("This action cannot be undone.")
                    },
                )
            }
        }
    )
}
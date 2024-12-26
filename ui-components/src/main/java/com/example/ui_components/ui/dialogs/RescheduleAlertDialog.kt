package com.example.ui_components.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.theme.Cream

@Composable
fun RescheduleAlertDialog(
    modifier: Modifier = Modifier,
    dateField: String,
    timeField: String,
    onUpdateDateField: (Long) -> Unit,
    onUpdateTimeField: (Int, Int) -> Unit,
    onRescheduleClicked: () -> Unit,
    onHideDialog: () -> Unit
) {
    var isDateDialogVisible by remember { mutableStateOf(false) }
    var isTimeDialogVisible by remember { mutableStateOf(false) }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onHideDialog() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(2) { index ->
                    val btn = when (index) {
                        0 -> listOf(Cream, Color.Black, "Reschedule")
                        1 -> listOf(Color.Transparent, Color.DarkGray, "Cancel")
                        else -> emptyList()
                    }
                    Button(
                        modifier = Modifier
                            .wrapContentWidth(),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.DarkGray
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = btn[0] as Color,
                            contentColor = btn[1] as Color
                        ),
                        onClick = {
                            when (index) {
                                0 -> onRescheduleClicked()
                                1 -> onHideDialog()
                            }
                        }
                    ) {
                        Text(
                            text = btn[2] as String,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            maxLines = 1
                        )
                    }
                }
            }
        },
        title = { Text(text = "Re-schedule Request") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(2) { index ->
                    val btn = when (index) {
                        0 -> dateField.ifEmpty { "Date" }
                        1 -> timeField.ifEmpty { "Time" }
                        else -> ""
                    }
                    OutlinedButton(
                        onClick = {
                            when (index) {
                                0 -> isDateDialogVisible = true
                                1 -> isTimeDialogVisible = true
                            }
                        }
                    ) {
                        Text(text = btn, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                    if (index == 0) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth(.8f)
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = .8f))
                        )
                    }
                }
            }
        }
    )

    if (isDateDialogVisible) {
        DateAlertDialog(
            onDateSelected = { date ->
                date?.let {
                    onUpdateDateField(it)
                }
            },
            onHideDialog = { isDateDialogVisible = false }
        )
    } else if (isTimeDialogVisible) {
        TimeAlertDialog(
            onTimeSelected = {hour, minute ->
                onUpdateTimeField(hour, minute)
            },
            onHideDialog = { isTimeDialogVisible = false}
        )
    }
}
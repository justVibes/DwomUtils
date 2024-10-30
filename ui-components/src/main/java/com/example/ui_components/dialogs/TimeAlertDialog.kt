package com.example.ui_components.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeAlertDialog(
    modifier: Modifier = Modifier,
    onTimeSelected: (Int, Int) -> Unit,
    onHideDialog: () -> Unit
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false
    )
    AlertDialog(
        modifier = modifier,
        title = {
            Text(text = "Time")
        },
        onDismissRequest = { onHideDialog() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier
                        .clickable { onHideDialog() },
                    text = "Cancel"
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    modifier = Modifier
                        .clickable {
                            onTimeSelected(timePickerState.hour, timePickerState.minute)
                            onHideDialog()
                        },
                    text = "Ok"
                )
            }
        },
        text = {
            TimeInput(state = timePickerState)
        }
    )
}
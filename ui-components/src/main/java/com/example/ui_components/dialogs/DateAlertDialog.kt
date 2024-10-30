package com.example.ui_components.dialogs

import android.widget.Toast
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateAlertDialog(
    modifier: Modifier = Modifier,
    onDateSelected: (Long?) -> Unit,
    onHideDialog: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = { onHideDialog() },
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onHideDialog()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { onHideDialog() }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
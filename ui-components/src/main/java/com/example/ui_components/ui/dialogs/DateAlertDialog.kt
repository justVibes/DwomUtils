package com.example.ui_components.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateAlertDialog(
    modifier: Modifier = Modifier,
    initialSelectedDate: Long? = null,
    minYear: Int = LocalDate.now().year,
    maxYear: Int = LocalDate.now().year + 1,
    onHideDialog: () -> Unit,
    onDateSelected: (Long?) -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDate,
        yearRange = minYear..maxYear
    )
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
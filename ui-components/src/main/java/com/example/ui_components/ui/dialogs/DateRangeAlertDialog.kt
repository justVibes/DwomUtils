package com.example.ui_components.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeAlertDialog(
    modifier: Modifier = Modifier,
    initialSelectedStartDate: Long,
    initialSelectedEndDate: Long,
    minYear: Int = LocalDate.now().year,
    maxYear: Int = LocalDate.now().year + 1,
    onHideDialog: () -> Unit,
    onDateSelected: (Long?, Long?) -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = initialSelectedStartDate,
        initialSelectedEndDateMillis = initialSelectedEndDate,
        yearRange = minYear..maxYear
    )

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = { onHideDialog() },
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(dateRangePickerState.selectedStartDateMillis, dateRangePickerState.selectedEndDateMillis)
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
        DateRangePicker(dateRangePickerState)
    }
}
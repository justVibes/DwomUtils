package com.example.ui_components.ui.cards.appointment.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RescheduleAndCancelBtns(
    onResheduleClicked: () -> Unit,
    onCancelClicked: () -> Unit,
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(2) { index ->
            val btn = when (index) {
                0 -> listOf(
                    "Reschedule",
                    Color.Transparent,
                    MaterialTheme.colorScheme.onSurfaceVariant,
                    onResheduleClicked()
                )

                1 -> listOf(
                    "Cancel",
                    Color.Red.copy(.9f),
                    Color.White,
                    onCancelClicked()
                )

                else -> emptyList()
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    btn[3]
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = btn[1] as Color,
                    contentColor = btn[2] as Color
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (btn[1] != Color.Transparent) Color.Black.copy(alpha = .7f) else btn[2] as Color
                )
            ) {
                Text(btn[0] as String)
            }
        }
    }
}
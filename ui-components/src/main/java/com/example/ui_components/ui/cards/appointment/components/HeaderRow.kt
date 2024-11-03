package com.example.ui_components.ui.cards.appointment.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.example.ui_components.models.gigrequest.GigRequest
import com.example.ui_components.theme.BlueHighlight

@Composable
fun HeaderRow(
    request: GigRequest,
    isDetailsVisible: Boolean,
    onHideDetailsClicked: () -> Unit,
    onShowDetailsClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if (request.serviceProviderDetails.uid.isNotEmpty()) "Accepted By: " else "",
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(
            modifier = Modifier.clickable {
                if (isDetailsVisible) {
                    onHideDetailsClicked()
                } else {
                    onShowDetailsClicked()
                }
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = (if (!isDetailsVisible) " Show " else " Hide ") + "Details ",
                color = BlueHighlight
            )
            Icon(
                modifier = Modifier.rotate(if (!isDetailsVisible) 0f else 180f),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = BlueHighlight
            )
        }
    }
}
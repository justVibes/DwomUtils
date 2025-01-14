package com.example.ui_components.ui.cards.loaded.appointment_card.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui_components.models.gigrequest.GigRequest
import com.example.ui_components.ui.core.core_logic.AppointmentDateTimeUtil.daysUntilAppointment
import com.example.ui_components.ui.core.core_logic.TextStyling

@Composable
fun ServiceProviderCard(
    mainCardBg: Color,
    request: GigRequest,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(MaterialTheme.shapes.small)
            .background(color = mainCardBg)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = .5f
                    )
                )
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = .5f
                    )
                ),
            model = request.selectedWorker!!.photo.url,
            contentDescription = null
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TextStyling.ColorDifference(
                    text = request.selectedWorker!!.tagName,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    separator = '#'
                )
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(Color.Green.copy(alpha = .7f))
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = request.jobInfo.requiredTypeOfWorker!!.title.fmt,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = daysUntilAppointment(request.jobInfo.dateToDoJob),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f)
                )
            }
        }
    }

}
package com.example.ui_components.ui.cards.appointment.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.ui_components.models.gigrequest.GigRequest
import com.example.ui_components.ui.core.AppointmentDateTimeUtil.requestLifespan
import com.example.ui_components.ui.core.TextStyling

@Composable
fun AppointmentSummaryCard(
    modifier: Modifier = Modifier,
    request: GigRequest,
    mainCardBg: Color,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(MaterialTheme.shapes.small)
            .background(mainCardBg)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.small)
                .background(
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = .5f
                    )
                )
                .border(
                    width = 1.dp,
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = .5f
                    )
                ),
            model = request.requestInfo.selectedImages.firstOrNull()?.image?.toUri(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextStyling.ColorDifference(
                    text = request.requestInfo.selectedRequestOptions.first().title + "|" + request.requestInfo.selectedRequestOptions.first().option,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    separator = '|'
                )
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = request.requestInfo.selectedRequestOptions[1].option,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    Text(
                        text = requestLifespan(request.requestInfo.creationDate),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f)
                    )
                }
            }
        }
    }

}
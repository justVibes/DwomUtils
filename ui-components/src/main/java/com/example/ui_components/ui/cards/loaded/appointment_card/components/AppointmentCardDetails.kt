package com.example.ui_components.ui.cards.loaded.appointment_card.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui_components.models.gigrequest.GigRequest
import com.example.ui_components.ui.core.core_logic.conversion.DateTimeConversion

@Composable
fun AppointmentCardDetails(
    gigRequest: GigRequest,
    secondaryCardBg: Color,
    mimicMainCardBg: Color,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        CardDetail(
            secondaryCardBg = secondaryCardBg,
            detail = "Request ID#: " to gigRequest.jobInfo.jobUid,
            mimicMainCardBg = mimicMainCardBg
        )
        gigRequest.jobInfo.selectedJobOptions.forEach { option ->
            CardDetail(
                secondaryCardBg = secondaryCardBg,
                detail = (option.title + ": ") to option.option,
                mimicMainCardBg = mimicMainCardBg
            )
        }
        repeat(3) { index ->
            val detail = when (index) {
                0 -> "Date: " to DateTimeConversion.mmmDDYYYFormat(gigRequest.jobInfo.dateToDoJob.toLong())
                1 -> "Time: " to DateTimeConversion.hhMMAFormat(gigRequest.jobInfo.timeToDoJob.toLong())
                2 -> "Budget: " to gigRequest.jobInfo.payment!!.let {
                    "$" + it.staticPrice.ifEmpty { "${it.minBudget} - $${it.maxBudget}" }
                }

                else -> "{172:59}" to "{172:72}"
            }
            CardDetail(secondaryCardBg, detail, mimicMainCardBg)
        }
    }
}

@Composable
private fun CardDetail(
    secondaryCardBg: Color,
    detail: Pair<String, String>,
    mimicMainCardBg: Color
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .clip(MaterialTheme.shapes.small)
            .background(secondaryCardBg)
            .padding(vertical = 5.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = detail.first,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(mimicMainCardBg)
                .padding(5.dp),
            text = detail.second,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface.copy(.7f)
        )
    }
}
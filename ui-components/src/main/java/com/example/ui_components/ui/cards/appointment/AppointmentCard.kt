package com.example.ui_components.ui.cards.appointment

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui_components.models.gigrequest.GigRequest
import com.example.ui_components.ui.cards.appointment.components.AppointmentCardDetails
import com.example.ui_components.ui.cards.appointment.components.AppointmentSummaryCard
import com.example.ui_components.ui.cards.appointment.components.HeaderRow
import com.example.ui_components.ui.cards.appointment.components.RescheduleAndCancelBtns
import com.example.ui_components.ui.cards.appointment.components.ServiceProviderCard


@Composable
fun AppointmentCard(
    modifier: Modifier = Modifier,
    request: GigRequest,
    isHighlighted: Boolean,
    isDetailsVisible: Boolean,
    onHideDetailsClicked: () -> Unit,
    onShowDetailsClicked: () -> Unit,
    onRescheduleClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    val mimicMainCardBg =
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .6f)

    val mainCardBg =
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .4f)

    val secondaryCardBg =
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .2f)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .background(mainCardBg)
            .border(
                width = 2.dp,
                shape = MaterialTheme.shapes.small,
                color = if (isHighlighted) MaterialTheme.colorScheme.primary else Color.Transparent
            )
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderRow(
            request = request,
            isDetailsVisible = isDetailsVisible,
            onHideDetailsClicked = onHideDetailsClicked,
            onShowDetailsClicked = onShowDetailsClicked
        )

        if (request.serviceProviderDetails.uid.isNotEmpty()) {
            ServiceProviderCard(mainCardBg, request)
        } else {
            AppointmentSummaryCard(Modifier, request, mainCardBg)
        }

        AnimatedVisibility(isDetailsVisible) {
            AppointmentCardDetails(request, secondaryCardBg, mimicMainCardBg)
        }

        RescheduleAndCancelBtns(
            onResheduleClicked = onRescheduleClicked,
            onCancelClicked = onCancelClicked
        )
    }
}
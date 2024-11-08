package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.components.EditType
import com.example.ui_components.models.client.components.components.stringComparisonEditType
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ClientVitals(
    @PrimaryKey
    var clientId: String = "",
    val heartRate: String = "",
    val respiratoryRate: String = "",
    val bloodPressure: String = "",
    val bloodOxygen: String = "",
    val bodyTemperatureCel: String = "",
) {
    object MapToHighlighted {
        fun from(original: ClientVitals, modified: ClientVitals): HighlightedClientVitals {
            return HighlightedClientVitals(
                clientId = stringComparisonEditType(original.clientId, modified.clientId),
                heartRate = stringComparisonEditType(original.heartRate, modified.heartRate),
                respiratoryRate = stringComparisonEditType(original.respiratoryRate, modified.respiratoryRate),
                bloodPressure = stringComparisonEditType(original.bloodPressure, modified.bloodPressure),
                bloodOxygen = stringComparisonEditType(original.bloodOxygen, modified.bloodOxygen),
                bodyTemperatureCel = stringComparisonEditType(original.bodyTemperatureCel, modified.bodyTemperatureCel),
            )
        }
    }
}


data class HighlightedClientVitals(
    val clientId: EditType = EditType.NONE,
    val heartRate: EditType = EditType.NONE,
    val respiratoryRate: EditType = EditType.NONE,
    val bloodPressure: EditType = EditType.NONE,
    val bloodOxygen: EditType = EditType.NONE,
    val bodyTemperatureCel: EditType = EditType.NONE,
)


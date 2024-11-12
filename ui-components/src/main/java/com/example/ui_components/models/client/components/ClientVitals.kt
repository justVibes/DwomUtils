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
    object Config {
        fun mapToHighlighted(
            original: ClientVitals,
            modified: ClientVitals
        ): HighlightedClientVitals {
            return HighlightedClientVitals(
                clientId = stringComparisonEditType(original.clientId, modified.clientId),
                heartRate = stringComparisonEditType(original.heartRate, modified.heartRate),
                respiratoryRate = stringComparisonEditType(
                    original.respiratoryRate,
                    modified.respiratoryRate
                ),
                bloodPressure = stringComparisonEditType(
                    original.bloodPressure,
                    modified.bloodPressure
                ),
                bloodOxygen = stringComparisonEditType(original.bloodOxygen, modified.bloodOxygen),
                bodyTemperatureCel = stringComparisonEditType(
                    original.bodyTemperatureCel,
                    modified.bodyTemperatureCel
                ),
            )
        }

        fun trimmedFields(form: ClientVitals) =
            form.copy(
                clientId = form.clientId.trim(),
                heartRate = form.heartRate.trim(),
                respiratoryRate = form.respiratoryRate.trim(),
                bloodPressure = form.bloodPressure.trim(),
                bloodOxygen = form.bloodOxygen.trim(),
                bodyTemperatureCel = form.bodyTemperatureCel.trim(),
            )


        fun mapToString(form: ClientVitals): String {
            val formattedForm = trimmedFields(form)
            return """
                Heart Rate: ${form.heartRate.ifEmpty { "n/a" }}
                Respiratory Rate: ${form.respiratoryRate.ifEmpty { "n/a" }}
                Blood Pressure: ${form.bloodPressure.ifEmpty { "n/a" }}
                Blood Oxygen: ${form.bloodOxygen.ifEmpty { "n/a" }}
                Body Temperature: ${form.bodyTemperatureCel.ifEmpty { "n/a" }}${Typography.degree}cel 
            """.trimIndent()
        }

        fun mapToListOfPairs(form: ClientVitals): List<Pair<String, String>> {
            val formattedForm = trimmedFields(form)
            return listOf(
                Pair("Heart Rate", form.heartRate.ifEmpty { "n/a" }),
                Pair("Respiratory Rate", form.respiratoryRate.ifEmpty { "n/a" }),
                Pair("Blood Pressure", form.bloodPressure.ifEmpty { "n/a" }),
                Pair("Blood Oxygen", form.bloodOxygen.ifEmpty { "n/a" }),
                Pair(
                    "Body Temperature",
                    "${form.bodyTemperatureCel.ifEmpty { "n/a" }}${Typography.degree}cel"
                ),
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


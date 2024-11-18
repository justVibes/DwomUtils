package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.core.EditType
import com.example.ui_components.models.client.components.core.stringComparison
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
                clientId = stringComparison(original.clientId, modified.clientId),
                heartRate = stringComparison(original.heartRate, modified.heartRate),
                respiratoryRate = stringComparison(
                    original.respiratoryRate,
                    modified.respiratoryRate
                ),
                bloodPressure = stringComparison(
                    original.bloodPressure,
                    modified.bloodPressure
                ),
                bloodOxygen = stringComparison(original.bloodOxygen, modified.bloodOxygen),
                bodyTemperatureCel = stringComparison(
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
                Heart Rate: ${formattedForm.heartRate.ifEmpty { "n/a" }}
                Respiratory Rate: ${formattedForm.respiratoryRate.ifEmpty { "n/a" }}
                Blood Pressure: ${formattedForm.bloodPressure.ifEmpty { "n/a" }}
                Blood Oxygen: ${formattedForm.bloodOxygen.ifEmpty { "n/a" }}
                Body Temperature: ${formattedForm.bodyTemperatureCel.ifEmpty { "n/a" }}${Typography.degree}cel 
            """.trimIndent()
        }

        fun mapToListOfPairs(form: ClientVitals): List<Pair<String, String>> {
            val formattedForm = trimmedFields(form)
            return listOf(
                Pair("Heart Rate", formattedForm.heartRate.ifEmpty { "n/a" }),
                Pair("Respiratory Rate", formattedForm.respiratoryRate.ifEmpty { "n/a" }),
                Pair("Blood Pressure", formattedForm.bloodPressure.ifEmpty { "n/a" }),
                Pair("Blood Oxygen", formattedForm.bloodOxygen.ifEmpty { "n/a" }),
                Pair(
                    "Body Temperature",
                    "${formattedForm.bodyTemperatureCel.ifEmpty { "n/a" }}${Typography.degree}cel"
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


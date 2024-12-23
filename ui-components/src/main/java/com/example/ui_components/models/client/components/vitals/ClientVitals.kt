package com.example.ui_components.models.client.components.vitals

import com.example.ui_components.models.client.components.core.stringComparison
import com.example.ui_components.models.client.components.vitals.variants.HighlightedClientVitals
import com.example.ui_components.models.client.components.vitals.variants.LocalClientVitals
import kotlinx.serialization.Serializable

@Serializable
data class ClientVitals(
    val heartRate: String = "",
    val respiratoryRate: String = "",
    val bloodPressure: String = "",
    val bloodOxygen: String = "",
    val bodyTemperatureCel: String = "",
) {
    companion object {
        fun mapToLocal(form: ClientVitals) = LocalClientVitals().apply {
            val formattedForm = trimmedFields(form)
            heartRate = formattedForm.heartRate
            respiratoryRate = formattedForm.respiratoryRate
            bloodPressure = formattedForm.bloodPressure
            bloodOxygen = formattedForm.bloodOxygen
            bodyTemperatureCel = formattedForm.bodyTemperatureCel
        }

        fun mapToHighlighted(
            original: ClientVitals?,
            modified: ClientVitals?
        ): HighlightedClientVitals {
            return HighlightedClientVitals(
                heartRate = stringComparison(original?.heartRate, modified?.heartRate),
                respiratoryRate = stringComparison(
                    original?.respiratoryRate,
                    modified?.respiratoryRate
                ),
                bloodPressure = stringComparison(
                    original?.bloodPressure,
                    modified?.bloodPressure
                ),
                bloodOxygen = stringComparison(original?.bloodOxygen, modified?.bloodOxygen),
                bodyTemperatureCel = stringComparison(
                    original?.bodyTemperatureCel,
                    modified?.bodyTemperatureCel
                ),
            )
        }

        fun trimmedFields(form: ClientVitals?) =
            form?.copy(
                heartRate = form.heartRate.trim(),
                respiratoryRate = form.respiratoryRate.trim(),
                bloodPressure = form.bloodPressure.trim(),
                bloodOxygen = form.bloodOxygen.trim(),
                bodyTemperatureCel = form.bodyTemperatureCel.trim(),
            ) ?: ClientVitals()


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



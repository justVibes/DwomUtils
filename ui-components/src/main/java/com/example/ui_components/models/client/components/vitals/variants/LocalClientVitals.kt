package com.example.ui_components.models.client.components.vitals.variants

import com.example.ui_components.models.client.components.vitals.ClientVitals
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalClientVitals : EmbeddedRealmObject {
    var heartRate: String = ""
    var respiratoryRate: String = ""
    var bloodPressure: String = ""
    var bloodOxygen: String = ""
    var bodyTemperatureCel: String = ""

    object Config {
        fun mapToOriginal(form: LocalClientVitals): ClientVitals {
            val formattedForm = trimmedFields(form)
            return ClientVitals(
                heartRate = formattedForm.heartRate,
                respiratoryRate = formattedForm.respiratoryRate,
                bloodPressure = formattedForm.bloodPressure,
                bloodOxygen = formattedForm.bloodOxygen,
                bodyTemperatureCel = formattedForm.bodyTemperatureCel,
            )
        }

        fun trimmedFields(form: LocalClientVitals) = form.apply {
            heartRate = heartRate.trim()
            respiratoryRate = respiratoryRate.trim()
            bloodPressure = bloodPressure.trim()
            bloodOxygen = bloodOxygen.trim()
            bodyTemperatureCel = bodyTemperatureCel.trim()
        }
    }
}
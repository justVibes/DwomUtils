package com.example.ui_components.models.client.components.emergency_contact_info

import com.example.ui_components.models.client.components.core.stringComparison
import com.example.ui_components.models.client.components.emergency_contact_info.variants.HighlightedEmergencyContactInfo
import com.example.ui_components.models.client.components.emergency_contact_info.variants.LocalEmergencyContactInfo
import kotlinx.serialization.Serializable

@Serializable
data class EmergencyContactInfo(
    var name: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var presentAddress: String = "",
) {
    object Config {
        fun mapToLocal(form: EmergencyContactInfo) = LocalEmergencyContactInfo().apply {
            val formattedForm = trimmedFields(form)
            name = formattedForm.name
            phoneNumber = formattedForm.phoneNumber
            email = formattedForm.email
            presentAddress = formattedForm.presentAddress
        }

        fun mapToHighlighted(
            original: EmergencyContactInfo?,
            modified: EmergencyContactInfo?
        ): HighlightedEmergencyContactInfo {
            return HighlightedEmergencyContactInfo(
                name = stringComparison(original?.name, modified?.name),
                phoneNumber = stringComparison(original?.phoneNumber, modified?.phoneNumber),
                email = stringComparison(original?.email, modified?.email),
                presentAddress = stringComparison(
                    original?.presentAddress,
                    modified?.presentAddress
                ),
            )
        }

        fun trimmedFields(form: EmergencyContactInfo?) =
            form?.copy(
                name = form.name.trim(),
                phoneNumber = form.phoneNumber.trim(),
                email = form.email.trim(),
                presentAddress = form.presentAddress.trim()
            ) ?: EmergencyContactInfo()

        fun mapToString(form: EmergencyContactInfo): String {
            val formattedForm = trimmedFields(form)
            return """
                Name: ${formattedForm.name.ifEmpty { "n/a" }}
                Phone-number: ${formattedForm.phoneNumber.ifEmpty { "n/a" }}
                Email: ${formattedForm.email.ifEmpty { "n/a" }}
                Present Address: ${formattedForm.presentAddress.ifEmpty { "n/a" }}
            """.trimIndent()
        }

        fun mapToListOfPairs(form: EmergencyContactInfo): List<Pair<String, String>> {
            val formattedForm = trimmedFields(form)
            return listOf(
                Pair("Name", formattedForm.name.ifEmpty { "n/a" }),
                Pair("Phone-number", formattedForm.phoneNumber.ifEmpty { "n/a" }),
                Pair("Email", formattedForm.email.ifEmpty { "n/a" }),
                Pair("Present Address", formattedForm.presentAddress.ifEmpty { "n/a" }),
            )
        }
    }
}



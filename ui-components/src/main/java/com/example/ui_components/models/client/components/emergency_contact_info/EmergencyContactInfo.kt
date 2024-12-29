package com.example.ui_components.models.client.components.emergency_contact_info

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.client.components.core.stringComparison
import com.example.ui_components.models.client.components.emergency_contact_info.variants.HighlightedEmergencyContactInfo
import com.example.ui_components.models.client.components.emergency_contact_info.variants.LocalEmergencyContactInfo
import kotlinx.serialization.Serializable



@Serializable
data class EmergencyContactInfo(
    val name: Name = Name(),
    val phoneNumber: String = "",
    val email: String = "",
    val relationship: String = "", /* (optional) Use 'AllRelationships' object to initialize */
    val presentAddress: String = "",
) {
    companion object {
        fun mapToLocal(form: EmergencyContactInfo) = LocalEmergencyContactInfo().apply {
            val formattedForm = trimmedFields(form)
            name = Name.mapToLocal(formattedForm.name)
            phoneNumber = formattedForm.phoneNumber
            email = formattedForm.email
            relationship = form.relationship
            presentAddress = formattedForm.presentAddress
        }

        fun mapToHighlighted(
            original: EmergencyContactInfo?,
            modified: EmergencyContactInfo?
        ): HighlightedEmergencyContactInfo {
            return HighlightedEmergencyContactInfo(
                firstName = stringComparison(original?.name?.first, modified?.name?.first),
                middleName = stringComparison(original?.name?.middle, modified?.name?.middle),
                lastName = stringComparison(original?.name?.last, modified?.name?.last),
                phoneNumber = stringComparison(original?.phoneNumber, modified?.phoneNumber),
                email = stringComparison(original?.email, modified?.email),
                presentAddress = stringComparison(
                    original?.presentAddress,
                    modified?.presentAddress
                ),
                relationship = stringComparison(original?.relationship, modified?.relationship)
            )
        }

        fun trimmedFields(form: EmergencyContactInfo?) =
            form?.copy(
                name = Name.trimmedFields(form.name),
                phoneNumber = form.phoneNumber.trim(),
                email = form.email.trim(),
                presentAddress = form.presentAddress.trim(),
                relationship = form.relationship.trim()
            ) ?: EmergencyContactInfo()

        fun mapToString(form: EmergencyContactInfo): String {
            val formattedForm = trimmedFields(form)
            return """
                Name: ${
                formattedForm.name.let { "${it.first} ${if (it.middle.isNotEmpty()) "${it.middle} " else ""}${it.last}" }
                    .ifEmpty { "n/a" }
            }
                Phone-number: ${formattedForm.phoneNumber.ifEmpty { "n/a" }}
                Email: ${formattedForm.email.ifEmpty { "n/a" }}
                Present Address: ${formattedForm.presentAddress.ifEmpty { "n/a" }}
            """.trimIndent()
        }

        fun mapToListOfPairs(form: EmergencyContactInfo): List<Pair<String, String>> {
            val formattedForm = trimmedFields(form)
            return listOf(
                Pair(
                    "Name",
                    formattedForm.name.let { "${it.first} ${if (it.middle.isNotEmpty()) "${it.middle} " else ""}${it.last}" }
                        .ifEmpty { "n/a" }
                ),
                Pair("Phone-number", formattedForm.phoneNumber.ifEmpty
                { "n/a" }),
                Pair("Email", formattedForm.email.ifEmpty
                { "n/a" }),
                Pair("Present Address", formattedForm.presentAddress.ifEmpty
                { "n/a" }),
            )
        }
    }
}



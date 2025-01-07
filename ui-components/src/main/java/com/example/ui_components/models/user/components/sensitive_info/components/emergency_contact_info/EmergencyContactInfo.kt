package com.example.ui_components.models.user.components.sensitive_info.components.emergency_contact_info

import com.example.ui_components.models.core.name.Name
import com.example.ui_components.models.user.components.sensitive_info.components.emergency_contact_info.variants.LocalEmergencyContactInfo
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



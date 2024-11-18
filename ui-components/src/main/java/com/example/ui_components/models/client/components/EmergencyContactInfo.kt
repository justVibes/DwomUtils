package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.core.EditType
import com.example.ui_components.models.client.components.core.stringComparison
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class EmergencyContactInfo(
    @PrimaryKey
    var clientId: String = "",
    var name: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var presentAddress: String = "",
) {
    object Config {
        fun mapToHighlighted(
            original: EmergencyContactInfo,
            modified: EmergencyContactInfo
        ): HighlightedEmergencyContactInfo {
            return HighlightedEmergencyContactInfo(
                clientId = stringComparison(original.clientId, modified.clientId),
                name = stringComparison(original.name, modified.name),
                phoneNumber = stringComparison(original.phoneNumber, modified.phoneNumber),
                email = stringComparison(original.email, modified.email),
                presentAddress = stringComparison(
                    original.presentAddress,
                    modified.presentAddress
                ),
            )
        }

        fun trimmedFields(form: EmergencyContactInfo) =
            form.copy(
                name = form.name.trim(),
                phoneNumber = form.phoneNumber.trim(),
                email = form.email.trim(),
                presentAddress = form.presentAddress.trim()
            )

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


data class HighlightedEmergencyContactInfo(
    val clientId: EditType = EditType.NONE,
    val name: EditType = EditType.NONE,
    val phoneNumber: EditType = EditType.NONE,
    val email: EditType = EditType.NONE,
    val presentAddress: EditType = EditType.NONE,
)
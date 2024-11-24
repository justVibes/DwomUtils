package com.example.ui_components.models.client.components.info

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.core.EditType
import com.example.ui_components.models.client.components.core.stringComparison
import com.example.ui_components.models.client.components.info.components.ClientPhoto
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ClientInfo(
    @PrimaryKey
    var clientId: String = "",
    var tagName: String = "",
    @Ignore var photo: ClientPhoto = ClientPhoto(),
    var firstName: String = "",
    var lastName: String = "",
    var sex: String = "", /*Use 'ValidGenders' enum to initialize*/
    val birthDate: String = "",
    var birthPlace: String = "",
    var height: String = "",
    var weight: String = "",
    var presentAddress: String = "",
    var occupation: String = "",
    var age: Int = 0, /*Generated based on the given birth date*/
    var localPhoneNumber: String = "",
    var emailAddress: String = "",
) {
    object Config {
        fun mapToHighlighted(original: ClientInfo, modified: ClientInfo) = HighlightedClientInfo(
            clientId = stringComparison(original.clientId, modified.clientId),
            tagName = stringComparison(original.tagName, modified.tagName),
            firstName = stringComparison(original.firstName, modified.firstName),
            lastName = stringComparison(original.lastName, modified.lastName),
            sex = stringComparison(original.sex, modified.sex),
            birthDate = stringComparison(original.birthDate, modified.birthDate),
            birthPlace = stringComparison(original.birthPlace, modified.birthPlace),
            height = stringComparison(original.height, modified.height),
            weight = stringComparison(original.weight, modified.weight),
            presentAddress = stringComparison(
                original.presentAddress,
                modified.presentAddress
            ),
            occupation = stringComparison(original.occupation, modified.occupation),
            localPhoneNumber = stringComparison(
                original.localPhoneNumber,
                modified.localPhoneNumber
            ),
            emailAddress = stringComparison(original.emailAddress, modified.emailAddress),
        )

        fun trimmedFields(form: ClientInfo) =
            form.copy(
                clientId = form.clientId.trim(),
                tagName = form.tagName.trim(),
                firstName = form.firstName.trim(),
                lastName = form.lastName.trim(),
                sex = form.sex.trim(),
                birthDate = form.birthDate.trim(),
                birthPlace = form.birthPlace.trim(),
                height = form.height.trim(),
                weight = form.weight.trim(),
                presentAddress = form.presentAddress.trim(),
                occupation = form.occupation.trim(),
                localPhoneNumber = form.localPhoneNumber.trim(),
                emailAddress = form.emailAddress.trim(),
            )

        fun mapToString(form: ClientInfo): String {
            val formattedForm = trimmedFields(form)
            return """
                Firstname: ${formattedForm.firstName.ifEmpty { "n/a" }}
                Lastname: ${formattedForm.lastName.ifEmpty { "n/a" }}
                Sex: ${formattedForm.sex.ifEmpty { "n/a" }}
                D.O.B : ${formattedForm.birthDate.ifEmpty { "n/a" }}
                Place of birth: ${formattedForm.birthPlace.ifEmpty { "n/a" }}
                Height: ${formattedForm.height.ifEmpty { "n/a" }}
                Weight: ${formattedForm.weight.ifEmpty { "n/a" }}
                Present Address: ${formattedForm.presentAddress.ifEmpty { "n/a" }}
                Occupation: ${formattedForm.occupation.ifEmpty { "n/a" }}
                Phone-number: ${formattedForm.localPhoneNumber.ifEmpty { "n/a" }}
                Email Address: ${formattedForm.emailAddress.ifEmpty { "n/a" }}
            """.trimIndent()
        }

        fun mapToListOfPairs(form: ClientInfo): List<Pair<String, String>> {
            val formattedForm = trimmedFields(form)
            return listOf(
                "Firstname" to formattedForm.firstName.ifEmpty { "n/a" },
                "Lastname" to formattedForm.lastName.ifEmpty { "n/a" },
                "Sex" to formattedForm.sex.ifEmpty { "n/a" },
                "D.O.B" to formattedForm.birthDate.ifEmpty { "n/a" },
                "Place of birth" to formattedForm.birthPlace.ifEmpty { "n/a" },
                "Height" to formattedForm.height.ifEmpty { "n/a" },
                "Weight" to formattedForm.weight.ifEmpty { "n/a" },
                "PresentAddress" to formattedForm.presentAddress.ifEmpty { "n/a" },
                "Occupation" to formattedForm.occupation.ifEmpty { "n/a" },
                "Phone-number" to formattedForm.localPhoneNumber.ifEmpty { "n/a" },
                "EmailAddress" to formattedForm.emailAddress.ifEmpty { "n/a" },
            )
        }
    }
}


data class HighlightedClientInfo(
    var clientId: EditType = EditType.NONE,
    var tagName: EditType = EditType.NONE,
    var photoUrl: EditType = EditType.NONE,
    var firstName: EditType = EditType.NONE,
    var lastName: EditType = EditType.NONE,
    var sex: EditType = EditType.NONE,
    val birthDate: EditType = EditType.NONE,
    var birthPlace: EditType = EditType.NONE,
    var height: EditType = EditType.NONE,
    var weight: EditType = EditType.NONE,
    var presentAddress: EditType = EditType.NONE,
    var occupation: EditType = EditType.NONE,
    var localPhoneNumber: EditType = EditType.NONE,
    var emailAddress: EditType = EditType.NONE,
)


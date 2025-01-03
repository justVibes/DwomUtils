package com.example.ui_components.models.client.components.info

import com.example.ui_components.models.client.components.core.name.Name
import com.example.ui_components.models.client.components.core.stringComparison
import com.example.ui_components.models.client.components.financial_info.ClientFinancialInfo
import com.example.ui_components.models.client.components.info.components.ClientPhoto
import com.example.ui_components.models.client.components.info.components.LocalClientPhoto
import com.example.ui_components.models.client.components.info.variants.HighlightedClientInfo
import com.example.ui_components.models.client.components.info.variants.LocalClientInfo
import com.example.ui_components.models.client.components.medical_info.ClientMedicalInfo
import com.example.ui_components.ui.core.core_logic.conversion.DateTimeConversion
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.Calendar

@Serializable
data class ClientInfo(
    val tagName: String = "",
    @Transient val photo: ClientPhoto = ClientPhoto(),
    val name: Name = Name(),
    val sex: String = "", /*Use 'ValidGenders' enum to initialize*/
    val birthDate: Long = 0L,
    val birthPlace: String = "",
    val height: String = "",
    val weight: String = "",
    val presentAddress: String = "",
    val occupation: String = "",
    val age: Int = (Calendar.getInstance().timeInMillis - birthDate).toInt(), /*Generated based on the given birth date*/
    val localPhoneNumber: String = "",
    val emailAddress: String = "",
    val medicalInfo: ClientMedicalInfo = ClientMedicalInfo(),
    val financialInfo: ClientFinancialInfo = ClientFinancialInfo()
) {
    companion object {
        fun mapToLocal(form: ClientInfo) = LocalClientInfo().apply {
            val formattedForm = trimmedFields(form)
            tagName = formattedForm.tagName
            photo = LocalClientPhoto().apply {
                url = form.photo.url
                updatedUrl = form.photo.updatedUrl
                storagePath = form.photo.storagePath
            }
            name = Name.mapToLocal(formattedForm.name)
            sex = formattedForm.sex  /*Use 'ValidGenders' enum to initialize*/
            birthDate = formattedForm.birthDate
            birthPlace = formattedForm.birthPlace
            height = formattedForm.height
            weight = formattedForm.weight
            presentAddress = formattedForm.presentAddress
            occupation = formattedForm.occupation
            age = 0 /*Generated based on the given birth date*/
            localPhoneNumber = formattedForm.localPhoneNumber
            emailAddress = formattedForm.emailAddress
            medicalInfo = ClientMedicalInfo.mapToLocal(formattedForm.medicalInfo)
            financialInfo = ClientFinancialInfo.mapToLocal(formattedForm.financialInfo)
        }

        fun mapToHighlighted(original: ClientInfo?, modified: ClientInfo?) = HighlightedClientInfo(
            tagName = stringComparison(original?.tagName, modified?.tagName),
            firstName = stringComparison(original?.name?.first, modified?.name?.first),
            middleName = stringComparison(original?.name?.middle, modified?.name?.middle),
            lastName = stringComparison(original?.name?.last, modified?.name?.last),
            sex = stringComparison(original?.sex, modified?.sex),
            birthDate = stringComparison("${original?.birthDate}", "${modified?.birthDate}"),
            birthPlace = stringComparison(original?.birthPlace, modified?.birthPlace),
            height = stringComparison(original?.height, modified?.height),
            weight = stringComparison(original?.weight, modified?.weight),
            presentAddress = stringComparison(
                original?.presentAddress,
                modified?.presentAddress
            ),
            occupation = stringComparison(original?.occupation, modified?.occupation),
            localPhoneNumber = stringComparison(
                original?.localPhoneNumber,
                modified?.localPhoneNumber
            ),
            emailAddress = stringComparison(original?.emailAddress, modified?.emailAddress),
        )

        fun trimmedFields(form: ClientInfo?) =
            form?.copy(
                tagName = form.tagName.trim(),
                name = Name.trimmedFields(form.name),
                sex = form.sex.trim(),
                birthDate = form.birthDate,
                birthPlace = form.birthPlace.trim(),
                height = form.height.trim(),
                weight = form.weight.trim(),
                presentAddress = form.presentAddress.trim(),
                occupation = form.occupation.trim(),
                localPhoneNumber = form.localPhoneNumber.trim(),
                emailAddress = form.emailAddress.trim(),
                medicalInfo = ClientMedicalInfo.trimmedFields(form.medicalInfo),
                financialInfo = ClientFinancialInfo.trimmedFields(form.financialInfo)
            ) ?: ClientInfo()

        fun mapToString(form: ClientInfo): String {
            val formattedForm = trimmedFields(form)
            return """
                Firstname: ${formattedForm.name.first.ifEmpty { "n/a" }}
                Lastname: ${formattedForm.name.last.ifEmpty { "n/a" }}
                Sex: ${formattedForm.sex.ifEmpty { "n/a" }}
                D.O.B : ${
                DateTimeConversion.mmmDDYYYFormat(formattedForm.birthDate).ifEmpty { "n/a" }
            }
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
                "Firstname" to formattedForm.name.first.ifEmpty { "n/a" },
                "Lastname" to formattedForm.name.last.ifEmpty { "n/a" },
                "Sex" to formattedForm.sex.ifEmpty { "n/a" },
                "D.O.B" to DateTimeConversion.mmmDDYYYFormat(formattedForm.birthDate)
                    .ifEmpty { "n/a" },
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




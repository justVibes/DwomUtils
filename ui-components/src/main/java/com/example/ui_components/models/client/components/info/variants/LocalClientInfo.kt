package com.example.ui_components.models.client.components.info.variants

import com.example.ui_components.models.client.components.core.name.LocalName
import com.example.ui_components.models.client.components.financial_info.variants.LocalClientFinancialInfo
import com.example.ui_components.models.client.components.info.ClientInfo
import com.example.ui_components.models.client.components.info.components.ClientPhoto
import com.example.ui_components.models.client.components.info.components.LocalClientPhoto
import com.example.ui_components.models.client.components.medical_info.variants.LocalClientMedicalInfo
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.annotations.Ignore
import java.util.Calendar

class LocalClientInfo : EmbeddedRealmObject {
    var name: LocalName? = null
    var tagName: String = ""
    var photo: LocalClientPhoto? = null
    var sex: String = ""
    var birthDate: Long = 0L
    var birthPlace: String = ""
    var height: String = ""
    var weight: String = ""
    @Ignore
    var age: Int = (Calendar.getInstance().timeInMillis - birthDate).toInt()
    var presentAddress: String = ""
    var occupation: String = ""
    var localPhoneNumber: String = ""
    var emailAddress: String = ""
    var medicalInfo: LocalClientMedicalInfo? = null
    var financialInfo: LocalClientFinancialInfo? = null

    object Config {
        fun mapToOriginal(form: LocalClientInfo): ClientInfo {
            val formattedForm = trimmedFields(form)
            return ClientInfo(
                tagName = formattedForm.tagName,
                photo = ClientPhoto(
                    url = form.photo?.url ?: "",
                    updatedUrl = form.photo?.updatedUrl ?: "",
                    storagePath = form.photo?.storagePath ?: ""
                ),
                name = LocalName.Config.mapToOriginal(formattedForm.name ?: LocalName()),
                sex = formattedForm.sex,
                birthDate = formattedForm.birthDate,
                birthPlace = formattedForm.birthPlace,
                height = formattedForm.height,
                weight = formattedForm.weight,
                presentAddress = formattedForm.presentAddress,
                occupation = formattedForm.occupation,
                age = form.age,
                localPhoneNumber = formattedForm.localPhoneNumber,
                emailAddress = formattedForm.emailAddress,
                medicalInfo = LocalClientMedicalInfo.Config.mapToOriginal(
                    formattedForm.medicalInfo ?: LocalClientMedicalInfo()
                ),
                financialInfo = LocalClientFinancialInfo.Config.mapToOriginal(
                    formattedForm.financialInfo ?: LocalClientFinancialInfo()
                )
            )
        }

        fun trimmedFields(form: LocalClientInfo) = LocalClientInfo().apply {
            tagName = form.tagName.trim()
            name = form.name?.let { LocalName.Config.trimmedFields(it) }
            sex = form.sex.trim()
            birthPlace = form.birthPlace.trim()
            height = form.height.filter { it.isDigit() }.trim()
            weight = form.weight.filter { it.isDigit() }.trim()
            presentAddress = form.presentAddress.trim()
            occupation = form.occupation.trim()
            localPhoneNumber = form.localPhoneNumber.filter { it.isDigit() }.trim()
            emailAddress = form.emailAddress.trim()
            medicalInfo = LocalClientMedicalInfo.Config.trimmedFields(
                form.medicalInfo ?: LocalClientMedicalInfo()
            )
            financialInfo = LocalClientFinancialInfo.Config.trimmedFields(
                form.financialInfo ?: LocalClientFinancialInfo()
            )
        }
    }
}
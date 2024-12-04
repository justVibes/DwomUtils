package com.example.ui_components.models.client.components.info.variants

import com.example.ui_components.models.client.components.info.ClientInfo
import com.example.ui_components.models.client.components.info.components.ClientPhoto
import com.example.ui_components.models.client.components.info.components.LocalClientPhoto
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.annotations.Ignore
import java.util.Calendar

class LocalClientInfo : EmbeddedRealmObject {
    var tagName: String = ""
    var photo: LocalClientPhoto? = null
    var firstName: String = ""
    var lastName: String = ""
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

    object Config {
        fun mapToOriginal(form: LocalClientInfo): ClientInfo {
            val formattedForm = trimmedFields(form)
            return ClientInfo(
                tagName = formattedForm.tagName,
                photo = ClientPhoto(
                    url = formattedForm.photo?.url ?: "",
                    updatedUrl = formattedForm.photo?.updatedUrl ?: "",
                    storagePath = formattedForm.photo?.storagePath ?: ""
                ),
                firstName = formattedForm.firstName,
                lastName = formattedForm.lastName,
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
            )
        }

        fun trimmedFields(form: LocalClientInfo) = LocalClientInfo().apply {
            tagName = form.tagName.trim()
            firstName = form.firstName.trim()
            lastName = form.lastName.trim()
            sex = form.sex.trim()
            birthPlace = form.birthPlace.trim()
            height = form.height.filter { it.isDigit() }.trim()
            weight = form.weight.filter { it.isDigit() }.trim()
            presentAddress = form.presentAddress.trim()
            occupation = form.occupation.trim()
            localPhoneNumber = form.localPhoneNumber.filter { it.isDigit() }.trim()
            emailAddress = form.emailAddress.trim()
        }
    }
}
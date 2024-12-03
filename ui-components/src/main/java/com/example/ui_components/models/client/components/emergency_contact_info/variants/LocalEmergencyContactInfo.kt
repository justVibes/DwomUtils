package com.example.ui_components.models.client.components.emergency_contact_info.variants

import com.example.ui_components.models.client.components.emergency_contact_info.EmergencyContactInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalEmergencyContactInfo: EmbeddedRealmObject {
    var name: String = ""
    var phoneNumber: String = ""
    var email: String = ""
    var presentAddress: String = ""

    object Config {
        fun mapToOriginal(form: LocalEmergencyContactInfo):EmergencyContactInfo {
            val formattedForm = trimmedFields(form)
            return EmergencyContactInfo(
                name = formattedForm.name,
                phoneNumber = formattedForm.phoneNumber,
                email = formattedForm.email,
                presentAddress = formattedForm.presentAddress
            )
        }

        fun trimmedFields(form: LocalEmergencyContactInfo) = LocalEmergencyContactInfo().apply {
            name = form.name.trim()
            phoneNumber = form.phoneNumber.trim()
            email = form.email.trim()
            presentAddress = form.presentAddress.trim()
        }
    }
}
package com.example.ui_components.models.user.components.sensitive_info.components.emergency_contact_info.variants

import com.example.ui_components.models.core.name.variants.LocalName
import com.example.ui_components.models.user.components.sensitive_info.components.emergency_contact_info.EmergencyContactInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalEmergencyContactInfo: EmbeddedRealmObject {
    var name: LocalName? = null
    var phoneNumber: String = ""
    var email: String = ""
    var relationship: String = ""
    var presentAddress: String = ""

    companion object {
        fun mapToOriginal(form: LocalEmergencyContactInfo): EmergencyContactInfo {
            val formattedForm = trimmedFields(form)
            return EmergencyContactInfo(
                name = LocalName.mapToOriginal(formattedForm.name ?: LocalName()),
                phoneNumber = formattedForm.phoneNumber,
                email = formattedForm.email,
                presentAddress = formattedForm.presentAddress,
                relationship = formattedForm.relationship
            )
        }

        fun trimmedFields(form: LocalEmergencyContactInfo) = LocalEmergencyContactInfo().apply {
            name = form.name?.let { LocalName.trimmedFields(it) }
            phoneNumber = form.phoneNumber.trim()
            email = form.email.trim()
            presentAddress = form.presentAddress.trim()
            relationship = form.relationship.trim()
        }
    }
}
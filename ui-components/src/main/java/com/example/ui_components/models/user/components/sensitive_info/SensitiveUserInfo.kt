package com.example.ui_components.models.user.components.sensitive_info

import com.example.ui_components.models.user.components.sensitive_info.components.emergency_contact_info.EmergencyContactInfo
import com.example.ui_components.models.user.components.sensitive_info.components.personal_user_info.PersonalUserInfo
import com.example.ui_components.models.user.components.sensitive_info.components.user_med_insurance.UserMedInsInfo

data class SensitiveUserInfo(
    val emergencyContact: EmergencyContactInfo? = null,
    val personal: PersonalUserInfo? = null,
    val medicalInsurance: UserMedInsInfo? = null
) {
    companion object {
        fun trimmedFields(form: SensitiveUserInfo) = form.copy(
            emergencyContact = form.emergencyContact.let { EmergencyContactInfo.trimmedFields(it) },
            personal = form.personal?.let { PersonalUserInfo.trimmedFields(it) },
            medicalInsurance = form.medicalInsurance?.let {
                UserMedInsInfo.trimmedFields(it)
            }
        )
    }
}
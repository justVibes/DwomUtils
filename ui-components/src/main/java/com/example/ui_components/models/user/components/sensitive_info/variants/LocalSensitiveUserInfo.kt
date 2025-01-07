package com.example.ui_components.models.user.components.sensitive_info.variants

import com.example.ui_components.models.user.components.sensitive_info.SensitiveUserInfo
import com.example.ui_components.models.user.components.sensitive_info.components.emergency_contact_info.variants.LocalEmergencyContactInfo
import com.example.ui_components.models.user.components.sensitive_info.components.personal_user_info.variants.LocalPersonalUserInfo
import com.example.ui_components.models.user.components.sensitive_info.components.user_med_insurance.variants.LocalUserMedInsInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalSensitiveUserInfo : EmbeddedRealmObject {
    var emergencyContact: LocalEmergencyContactInfo? = null
    var personal: LocalPersonalUserInfo? = null
    var medicalInsurance: LocalUserMedInsInfo? = null

    companion object {
        fun mapToOriginal(form: LocalSensitiveUserInfo): SensitiveUserInfo {
            val fmtForm = trimmedFields(form)
            return SensitiveUserInfo(
                emergencyContact = fmtForm.emergencyContact?.let {
                    LocalEmergencyContactInfo.mapToOriginal(it)
                },
                personal = fmtForm.personal?.let {
                    LocalPersonalUserInfo.mapToOriginal(it)
                },
                medicalInsurance = fmtForm.medicalInsurance?.let {
                    LocalUserMedInsInfo.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalSensitiveUserInfo) = LocalSensitiveUserInfo().apply {
            emergencyContact =
                form.emergencyContact?.let { LocalEmergencyContactInfo.trimmedFields(it) }
            personal = form.personal?.let { LocalPersonalUserInfo.trimmedFields(it) }
            medicalInsurance =
                form.medicalInsurance?.let { LocalUserMedInsInfo.trimmedFields(it) }
        }
    }
}
package com.example.ui_components.models.user.components.sensitive_info.components.user_med_insurance.variants

import com.example.ui_components.models.user.components.sensitive_info.components.user_med_insurance.UserMedInsInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalUserMedInsInfo : EmbeddedRealmObject {
    var memberId: String = ""
    var bin: String = ""
    var benefitPlan: String = ""
    var carrier: String = ""
    var plan: String = ""
    var effectiveDate: Long = 0L
    var expiryDate: Long = 0L

    companion object {
        fun mapToOriginal(form: LocalUserMedInsInfo): UserMedInsInfo {
            val fmtForm = trimmedFields(form)
            return UserMedInsInfo(
                memberId = fmtForm.memberId,
                bin = fmtForm.bin,
                benefitPlan = fmtForm.benefitPlan,
                carrier = fmtForm.carrier,
                plan = fmtForm.plan,
                effectiveDate = fmtForm.effectiveDate,
                expiryDate = fmtForm.expiryDate
            )
        }

        fun trimmedFields(form: LocalUserMedInsInfo) = LocalUserMedInsInfo()
            .apply {
                memberId = form.memberId.trim()
                bin = form.bin.trim()
                benefitPlan = form.benefitPlan.trim()
                carrier = form.carrier.trim()
                plan = form.plan.trim()
                effectiveDate = form.effectiveDate
                expiryDate = form.expiryDate
            }
    }
}
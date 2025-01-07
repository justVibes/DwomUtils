package com.example.ui_components.models.user.components.sensitive_info.components.user_med_insurance

import com.example.ui_components.models.user.components.sensitive_info.components.user_med_insurance.variants.LocalUserMedInsInfo


data class UserMedInsInfo(
    val memberId: String = "",
    val bin: String = "",
    val benefitPlan: String = "",
    val carrier: String = "",
    val plan: String = "",
    val effectiveDate: Long = 0L,
    val expiryDate: Long = 0L
) {
    companion object {
        fun mapToLocal(form: UserMedInsInfo) = LocalUserMedInsInfo()
            .apply {
            val fmtForm = trimmedFields(form)
            memberId = fmtForm.memberId
            bin = fmtForm.bin
            benefitPlan = fmtForm.benefitPlan
            carrier = fmtForm.carrier
            plan = fmtForm.plan
            effectiveDate = fmtForm.effectiveDate
            expiryDate = fmtForm.expiryDate
        }

        fun trimmedFields(form: UserMedInsInfo) = form.copy(
            memberId = form.memberId.trim(),
            bin = form.bin.trim(),
            benefitPlan = form.benefitPlan.trim(),
            carrier = form.carrier.trim(),
            plan = form.plan.trim(),
            effectiveDate = form.effectiveDate,
            expiryDate = form.expiryDate
        )
    }
}
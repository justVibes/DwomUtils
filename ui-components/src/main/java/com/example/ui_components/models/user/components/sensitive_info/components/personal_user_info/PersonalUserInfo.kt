package com.example.ui_components.models.user.components.sensitive_info.components.personal_user_info

import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.WorkPlace
import com.example.ui_components.models.user.components.sensitive_info.components.personal_user_info.variants.LocalPersonalUserInfo

data class PersonalUserInfo(
    val address: String = "",
    val birthPlace: String = "",
    val workPlace: WorkPlace = WorkPlace(),
    val trn: String = "",
    /*This is only initialized if the current user is attached to a company
    * that is using the app and has its own collection.
    */
    val companyCollectionPath: String? = null
) {
    companion object {
        fun mapToLocal(form: PersonalUserInfo) = LocalPersonalUserInfo().apply {
            val fmtForm = trimmedFields(form)
            address = fmtForm.address
            birthPlace = fmtForm.birthPlace
            workPlace = fmtForm.workPlace.let { WorkPlace.mapToLocal(it) }
            companyCollectionPath = fmtForm.companyCollectionPath
            trn = fmtForm.trn

        }

        fun trimmedFields(form: PersonalUserInfo) = form.copy(
            address = form.address.trim(),
            birthPlace = form.birthPlace.trim(),
            workPlace = form.workPlace.let { WorkPlace.trimmedFields(it) },
            trn = form.trn.filter { it.isDigit() },
            companyCollectionPath = form.companyCollectionPath?.trim()
        )
    }
}
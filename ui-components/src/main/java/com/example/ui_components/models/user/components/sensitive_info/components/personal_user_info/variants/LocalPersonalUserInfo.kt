package com.example.ui_components.models.user.components.sensitive_info.components.personal_user_info.variants

import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.WorkPlace
import com.example.ui_components.models.core.company.components.employee.components.public_info.components.workplace.variants.LocalWorkPlace
import com.example.ui_components.models.user.components.sensitive_info.components.personal_user_info.PersonalUserInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalPersonalUserInfo : EmbeddedRealmObject {
    var trn: String = ""
    var address: String = ""
    var birthPlace: String = ""
    var workPlace: LocalWorkPlace? = null

    /*This is only initialized if the current user is attached to a company
    * that is using the app and has its own collection.
    */
    var companyCollectionPath: String? = null

    companion object {
        fun mapToOriginal(form: LocalPersonalUserInfo): PersonalUserInfo {
            val fmtForm = trimmedFields(form)
            return PersonalUserInfo(
                address = fmtForm.address,
                birthPlace = fmtForm.birthPlace,
                workPlace = fmtForm.workPlace?.let { LocalWorkPlace.mapToOriginal(it) }
                    ?: WorkPlace(),
                companyCollectionPath = fmtForm.companyCollectionPath
            )
        }

        fun trimmedFields(form: LocalPersonalUserInfo) = LocalPersonalUserInfo().apply {
            address = form.address.trim()
            birthPlace = form.birthPlace.trim()
            workPlace = form.workPlace?.let { LocalWorkPlace.trimmedFields(it) }
            companyCollectionPath = form.companyCollectionPath?.trim()
        }
    }

}
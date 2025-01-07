package com.example.ui_components.models.user.components.medical_info.components.metadata.variants

import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.components.public_info.variants.LocalPublicEmployeeInfo
import com.example.ui_components.models.user.components.medical_info.components.metadata.ClientMedicalInfoMetadata
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalClientMedicalInfoMetadata : EmbeddedRealmObject {
    var createdBy: LocalPublicEmployeeInfo? = null
    var creationDate: Long = 0L
    var docPath: String = ""
    var accessibleCompanyPaths: RealmList<String> = realmListOf()

    companion object {
        fun mapToOriginal(form: LocalClientMedicalInfoMetadata): ClientMedicalInfoMetadata {
            val fmtForm = trimmedFields(form)
            return ClientMedicalInfoMetadata(
                createdBy = fmtForm.createdBy?.let { LocalPublicEmployeeInfo.mapToOriginal(it) }
                    ?: PublicEmployeeInfo(),
                creationDate = fmtForm.creationDate,
                docPath = fmtForm.docPath,
                accessibleCompanyPaths = fmtForm.accessibleCompanyPaths
            )
        }

        fun trimmedFields(form: LocalClientMedicalInfoMetadata) =
            LocalClientMedicalInfoMetadata().apply {
                createdBy = form.createdBy?.let { LocalPublicEmployeeInfo.trimmedFields(it) }
                creationDate = form.creationDate
                docPath = form.docPath.trim()
                accessibleCompanyPaths = form.accessibleCompanyPaths.map { it.trim() }.toRealmList()
            }
    }
}
package com.example.ui_components.models.user.components.medical_info.components.metadata

import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.user.components.medical_info.components.metadata.variants.LocalClientMedicalInfoMetadata
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable

@Serializable
data class ClientMedicalInfoMetadata(
    val createdBy: PublicEmployeeInfo = PublicEmployeeInfo(),
    val creationDate: Long = 0L,
    val docPath: String = "",
    val accessibleCompanyPaths: List<String> = emptyList()
){
    companion object {
        fun mapToLocal(form: ClientMedicalInfoMetadata) = LocalClientMedicalInfoMetadata().apply {
            val fmtForm = trimmedFields(form)
            createdBy = fmtForm.createdBy.let { PublicEmployeeInfo.mapToLocal(it) }
            creationDate = fmtForm.creationDate
            docPath = fmtForm.docPath
            accessibleCompanyPaths = fmtForm.accessibleCompanyPaths.toRealmList()
        }
        
        fun trimmedFields(form: ClientMedicalInfoMetadata) = form.copy(
            createdBy = form.createdBy.let { PublicEmployeeInfo.trimmedFields(it) },
            creationDate = form.creationDate,
            docPath = form.docPath.trim(),
            accessibleCompanyPaths = form.accessibleCompanyPaths.map { it.trim() }
        )
    }
}
package com.example.ui_components.models.user.variants

import com.example.ui_components.models.core.company.components.employee.components.public_info.variants.LocalPublicEmployeeInfo
import com.example.ui_components.models.core.name.Name
import com.example.ui_components.models.core.name.variants.LocalName
import com.example.ui_components.models.core.photo.UserPhoto
import com.example.ui_components.models.core.photo.variants.LocalUserPhoto
import com.example.ui_components.models.core.worker_title.WorkTitle
import com.example.ui_components.models.core.worker_title.variants.LocalWorkTitle
import com.example.ui_components.models.measurement.Measurement
import com.example.ui_components.models.measurement.variants.LocalMeasurement
import com.example.ui_components.models.user.UserModel
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalUserModel : EmbeddedRealmObject {
    var birthDate: Long = 0L
    var createdBy: LocalPublicEmployeeInfo? = null
    var email: String = ""
    var height: LocalMeasurement? = null
    var weight: LocalMeasurement? = null
    var name: LocalName? = null
    var phoneNumber: String = ""
    var photo: LocalUserPhoto? = null
    var sex: String = ""
    var tagName: String = ""
    var titleDocPath: String = ""
    var workTitle: LocalWorkTitle? = null

    companion object {
        fun mapToOriginal(form: LocalUserModel): UserModel {
            val fmtForm = trimmedFields(form)
            return UserModel(
                birthDate = fmtForm.birthDate,
                createdBy = fmtForm.createdBy?.let { LocalPublicEmployeeInfo.mapToOriginal(it) },
                email = fmtForm.email,
                height = fmtForm.height?.let { LocalMeasurement.mapToOriginal(it) }
                    ?: Measurement(),
                weight = fmtForm.weight?.let { LocalMeasurement.mapToOriginal(it) }
                    ?: Measurement(),
                name = fmtForm.name?.let { LocalName.mapToOriginal(it) } ?: Name(),
                phoneNumber = fmtForm.phoneNumber,
                photo = fmtForm.photo?.let { LocalUserPhoto.mapToOriginal(it) } ?: UserPhoto(),
                sex = fmtForm.sex,
                tagName = fmtForm.tagName,
                titleDocPath = fmtForm.titleDocPath,
                workTitle = fmtForm.workTitle?.let { LocalWorkTitle.mapToOriginal(it) }
                    ?: WorkTitle(),
            )
        }

        fun trimmedFields(form: LocalUserModel) = LocalUserModel().apply {
            birthDate = form.birthDate
            createdBy = form.createdBy?.let { LocalPublicEmployeeInfo.trimmedFields(it) }
            email = form.email.trim()
            height = form.height
            weight = form.weight
            name = form.name?.let { LocalName.trimmedFields(it) }
            phoneNumber = form.phoneNumber.trim()
            photo = form.photo?.let { LocalUserPhoto.trimmedFields(it) }
            sex = form.sex.trim()
            tagName = form.tagName.trim()
            titleDocPath = form.titleDocPath.trim()
            workTitle = form.workTitle?.let { LocalWorkTitle.trimmedFields(it) }
        }
    }
}
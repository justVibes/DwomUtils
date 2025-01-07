package com.example.ui_components.models.user

import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.core.name.Name
import com.example.ui_components.models.core.photo.UserPhoto
import com.example.ui_components.models.core.worker_title.WorkTitle
import com.example.ui_components.models.measurement.Measurement
import com.example.ui_components.models.user.components.freelancer_info.FreelancerInfo
import com.example.ui_components.models.user.components.medical_info.ClientMedicalInfo
import com.example.ui_components.models.user.components.sensitive_info.SensitiveUserInfo
import com.example.ui_components.models.user.variants.LocalUserModel
import java.util.Calendar

data class UserModel(
    /* This is only initialized if the account/user was created by an employee (like a receptionist) */
    val createdBy: PublicEmployeeInfo? = null,
    val email: String = "",
    val height: Measurement = Measurement(),
    val weight: Measurement = Measurement(),
    val name: Name = Name(),
    val phoneNumber: String = "",
    val photo: UserPhoto = UserPhoto(),
    val sex: String = "",
    val birthDate: Long = 0L,
    val tagName: String = "",
    val titleDocPath: String = "",
    /* This is initialized for both employees and regular clients
     * (indicating their position at their workplace)
     */
    val workTitle: WorkTitle = WorkTitle(),
    val accountHistory: List<UserModel>? = emptyList(),
    val sensitiveUserInfo: SensitiveUserInfo? = null,
    val medicalInfo: ClientMedicalInfo? = null,
    val freelancerInfo: FreelancerInfo? = null
) {
    val age
        get() = (Calendar.getInstance().timeInMillis - birthDate).toInt()
            .let { if (it < 0) "0" else "$it" }


    companion object {
        fun mapToLocal(form: UserModel) = LocalUserModel().apply {
            val fmtForm = trimmedFields(form)
            birthDate = fmtForm.birthDate
            createdBy = fmtForm.createdBy?.let { PublicEmployeeInfo.mapToLocal(it) }
            email = fmtForm.email
            height = fmtForm.height.let { Measurement.mapToOriginal(it) }
            weight = fmtForm.weight.let { Measurement.mapToOriginal(it) }
            name = fmtForm.name.let { Name.mapToLocal(it) }
            phoneNumber = fmtForm.phoneNumber
            photo = fmtForm.photo.let { UserPhoto.mapToLocal(it) }
            sex = fmtForm.sex
            tagName = fmtForm.tagName
            titleDocPath = fmtForm.titleDocPath
            workTitle = fmtForm.workTitle.let { WorkTitle.mapToLocal(it) }
        }

        fun trimmedFields(form: UserModel, isRecursive: Boolean = false): UserModel {
            val accountHistory = form.accountHistory?.map { trimmedFields(it, true) }
            return form.copy(
                birthDate = form.birthDate,
                createdBy = form.createdBy?.let { PublicEmployeeInfo.trimmedFields(it) },
                email = form.email.trim(),
                height = form.height,
                weight = form.weight,
                name = form.name.let { Name.trimmedFields(it) },
                phoneNumber = form.phoneNumber.filter { it.isDigit() },
                photo = form.photo.let { UserPhoto.trimmedFields(it) },
                sex = form.sex.trim(),
                tagName = form.tagName.trim(),
                titleDocPath = form.titleDocPath.trim(),
                accountHistory = if (isRecursive) null else accountHistory,
                workTitle = form.workTitle.let { WorkTitle.trimmedFields(it) },
            )
        }
    }
}






package com.example.ui_components.models.core.company.components.employee.variants

import com.example.ui_components.models.core.company.components.employee.Employee
import com.example.ui_components.models.core.company.components.employee.components.private_info.variants.LocalPrivateEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.components.public_info.variants.LocalPublicEmployeeInfo
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalEmployee : EmbeddedRealmObject {
    var publicInfo: LocalPublicEmployeeInfo? = null
    var privateEmployeeInfo: LocalPrivateEmployeeInfo? = null

    companion object {
        fun mapToOriginal(form: LocalEmployee): Employee {
            val fmtForm = trimmedFields(form)
            return Employee(
                publicInfo = fmtForm.publicInfo?.let { LocalPublicEmployeeInfo.mapToOriginal(it) }
                    ?: PublicEmployeeInfo(),
                privateEmployeeInfo = fmtForm.privateEmployeeInfo?.let {
                    LocalPrivateEmployeeInfo.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalEmployee) = LocalEmployee().apply {
            publicInfo = form.publicInfo?.let { LocalPublicEmployeeInfo.trimmedFields(it) }
            privateEmployeeInfo =
                form.privateEmployeeInfo?.let { LocalPrivateEmployeeInfo.trimmedFields(it) }
        }
    }
}
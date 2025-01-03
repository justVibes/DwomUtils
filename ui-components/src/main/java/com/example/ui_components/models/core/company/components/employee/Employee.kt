package com.example.ui_components.models.core.company.components.employee

import com.example.ui_components.models.core.company.components.CompanyFields
import com.example.ui_components.models.core.company.components.employee.components.private_info.PrivateEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.components.public_info.PublicEmployeeInfo
import com.example.ui_components.models.core.company.components.employee.variants.LocalEmployee


data class Employee(
    val publicInfo: PublicEmployeeInfo = PublicEmployeeInfo(),
    val privateEmployeeInfo: PrivateEmployeeInfo? = null
) {
    init {
        privateEmployeeInfo?.companyMetadata?.field?.lowercase()?.trim().let {
            if (it == CompanyFields.Medical.label &&
                CompanyFields.Medical.Titles.values().none { titles ->
                    titles.fmt == publicInfo.title.fmt.lowercase() &&
                            titles.abv == publicInfo.title.abv.lowercase()
                }
            ) {
                throw IllegalArgumentException("property: [title] isn't initialized with the 'EmployeeTitles' enum")
            }
        }
    }

    companion object {
        fun mapToLocal(form: Employee) = LocalEmployee().apply {
            val fmtForm = trimmedFields(form)
            publicInfo = fmtForm.publicInfo.let { PublicEmployeeInfo.mapToLocal(it) }
            privateEmployeeInfo =
                fmtForm.privateEmployeeInfo?.let { PrivateEmployeeInfo.mapToLocal(it) }
        }

        fun trimmedFields(form: Employee) = form.copy(
            publicInfo = form.publicInfo.let { PublicEmployeeInfo.trimmedFields(it) },
            privateEmployeeInfo = form.privateEmployeeInfo?.let {
                PrivateEmployeeInfo.trimmedFields(it)
            }
        )
    }
}


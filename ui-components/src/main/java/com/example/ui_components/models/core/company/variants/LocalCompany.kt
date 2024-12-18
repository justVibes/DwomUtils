package com.example.ui_components.models.core.company.variants

import com.example.ui_components.models.core.company.Company
import com.example.ui_components.models.core.company.components.company_summary.variants.LocalCompanySummary
import com.example.ui_components.models.core.company.components.doctor.variants.LocalDoctor
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalCompany : EmbeddedRealmObject {
    var companyId: String = ""
    var summary: LocalCompanySummary? = null
    var photoUrl: String = ""
    var coarseLocation: String = ""
    var type: String = ""
    var aboutUs: String = ""
    var employeesDocPaths: RealmList<String> = realmListOf()
    var employees: RealmList<LocalDoctor> = realmListOf()/*This is for local usage*/

    object Config {
        fun mapToOriginal(form: LocalCompany): Company {
            val formattedForm = trimmedFields(form)
            return Company(
                companyId = formattedForm.companyId,
                summary = formattedForm.summary?.let { LocalCompanySummary.Config.mapToOriginal(it) },
                photoUrl = formattedForm.photoUrl,
                coarseLocation = formattedForm.coarseLocation,
                type = formattedForm.type,
                aboutUs = formattedForm.aboutUs,
                employeesDocPaths = formattedForm.employeesDocPaths,
                doctors = formattedForm.employees.map { LocalDoctor.Config.mapToOriginal(it) }
            )
        }

        fun trimmedFields(form: LocalCompany) = LocalCompany().apply {
            companyId = form.companyId.trim()
            summary = form.summary?.let { LocalCompanySummary.Config.trimmedFields(it) }
            photoUrl = form.photoUrl.trim()
            coarseLocation = form.coarseLocation.trim()
            type = form.type.trim()
            aboutUs = form.aboutUs.trim()
            employeesDocPaths = form.employeesDocPaths.map { it.trim() }.toRealmList()
            employees = form.employees.map { LocalDoctor.Config.trimmedFields(it) }.toRealmList()
        }
    }
}
package com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.variants

import com.example.ui_components.models.client.components.color.CustomColor
import com.example.ui_components.models.client.components.color.variants.LocalCustomColor
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.EmployeeMetadata
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.components.company_attributes.CompanyAttributes
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.components.company_attributes.variants.LocalCompanyAttributes
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalEmployeeMetadata : EmbeddedRealmObject {
    var isAssistant: Boolean = false
    var partnerEmail: String? = null
    var hasAppointments: Boolean = false
    var bookedAppointments: List<LocalBookedAppointment>? =
        if (hasAppointments) emptyList() else null
    var assignedColor: LocalCustomColor? = null
    var nextAvailable: Long = 0L
    var docPath: String? = null
    var companyAttr: LocalCompanyAttributes? = null


    companion object {
        fun mapToOriginal(form: LocalEmployeeMetadata) = EmployeeMetadata(
            isAssistant = form.isAssistant,
            partnerEmail = form.partnerEmail?.trim(),
            hasAppointments = form.hasAppointments,
            bookedAppointments = form.bookedAppointments?.map {
                LocalBookedAppointment.mapToOriginal(it)
            },
            assignedColor = form.assignedColor?.let { LocalCustomColor.mapToOriginal(it) }
                ?: CustomColor(),
            nextAvailable = form.nextAvailable,
            docPath = form.docPath?.trim(),
            companyAttr = form.companyAttr?.let { LocalCompanyAttributes.mapToOriginal(it) }
                ?: CompanyAttributes()
        )
    }
}
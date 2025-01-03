package com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata

import com.example.ui_components.models.client.components.color.CustomColor
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.components.company_attributes.CompanyAttributes
import com.example.ui_components.models.core.company.components.employee.components.private_info.components.metadata.variants.LocalEmployeeMetadata
import io.realm.kotlin.ext.toRealmList

data class EmployeeMetadata(
    val isAssistant: Boolean = false,
    val partnerEmail: String? = null,
    val hasAppointments: Boolean = false,
    val bookedAppointments: List<BookedAppointment>? = if (hasAppointments) emptyList() else null,
    val assignedColor: CustomColor = CustomColor(rand1, rand2, rand3),
    val nextAvailable: Long = 0L,
    val docPath: String? = null,
    val companyAttr: CompanyAttributes = CompanyAttributes()
) {
    init {
        if (!hasAppointments && bookedAppointments != null) throw IllegalArgumentException("property: [bookedAppointments] can't be initialized when property: [hasAppointments] = false.")
    }

    companion object {
        private val rand1 = (20..235).random()
        private val rand2 = (20..235).random()
        private val rand3 = (20..235).random()
        fun mapToLocal(form: EmployeeMetadata) = LocalEmployeeMetadata().apply {
            isAssistant = form.isAssistant
            partnerEmail = form.partnerEmail?.trim()
            hasAppointments = form.hasAppointments
            bookedAppointments =
                form.bookedAppointments?.map { BookedAppointment.mapToLocal(it) }?.toRealmList()
            assignedColor = CustomColor.mapToLocal(form.assignedColor)
            nextAvailable = form.nextAvailable
            docPath = form.docPath?.trim()
            companyAttr = form.companyAttr.let { CompanyAttributes.mapToLocal(it) }
        }
    }
}
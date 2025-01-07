package com.example.ui_components.models.core.company.components.book_appointment.components.metadata

import com.example.ui_components.models.core.company.components.book_appointment.components.metadata.variants.LocalBookedAppointmentMetadata
import com.example.ui_components.models.core.company.components.metadata.CompanyMetadata

data class BookedAppointmentMetadata(
    val type: String = "", /* Use the 'BookedAppointmentType' enum to initialize */
    val positionInQueue: String = "",
    val status: String = "", /* Use 'BookedAppointmentStatus' enum to initialize */
    val approxStartTime: Long = 0L,
    val approxDurationInMins: Int = 0,
    val delayInMins: Int = 0,
    val advanceInMins: Int = 0,
    val companyMetadata: CompanyMetadata? = null,
    val companyCollectionPath: String? = null,
) {
    companion object {
        fun mapToLocal(form: BookedAppointmentMetadata) = LocalBookedAppointmentMetadata().apply {
            val fmtForm = trimmedFields(form)
            type = fmtForm.type
            positionInQueue = fmtForm.positionInQueue
            status = fmtForm.status
            approxStartTime = fmtForm.approxStartTime
            approxDurationInMins = fmtForm.approxDurationInMins
            delayInMins = fmtForm.delayInMins
            advanceInMins = fmtForm.advanceInMins
            companyMetadata = fmtForm.companyMetadata?.let { CompanyMetadata.mapToLocal(it) }
            companyCollectionPath = fmtForm.companyCollectionPath
        }

        fun trimmedFields(form: BookedAppointmentMetadata) = form.copy(
            type = form.type.trim(),
            positionInQueue = form.positionInQueue.trim(),
            status = form.status.trim(),
            approxStartTime = form.approxStartTime,
            approxDurationInMins = form.approxDurationInMins,
            delayInMins = form.delayInMins,
            advanceInMins = form.advanceInMins,
            companyMetadata = form.companyMetadata?.let { CompanyMetadata.trimmedFields(it) },
            companyCollectionPath = form.companyCollectionPath?.trim(),
        )
    }
}
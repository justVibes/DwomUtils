package com.example.ui_components.models.core.company.components.book_appointment.components.metadata.variants

import com.example.ui_components.models.core.company.components.book_appointment.components.metadata.BookedAppointmentMetadata
import com.example.ui_components.models.core.company.components.metadata.variants.LocalCompanyMetadata
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalBookedAppointmentMetadata : EmbeddedRealmObject {
    var type: String = ""
    var positionInQueue: String = ""
    var status: String = ""
    var approxStartTime: Long = 0L
    var approxDurationInMins: Int = 0
    var delayInMins: Int = 0
    var advanceInMins: Int = 0
    var companyMetadata: LocalCompanyMetadata? = null
    var companyCollectionPath: String? = null

    companion object {
        fun mapToOriginal(form: LocalBookedAppointmentMetadata): BookedAppointmentMetadata {
            val fmtForm = trimmedFields(form)
            return BookedAppointmentMetadata(
                type = fmtForm.type,
                positionInQueue = fmtForm.positionInQueue,
                status = fmtForm.status,
                approxStartTime = fmtForm.approxStartTime,
                approxDurationInMins = fmtForm.approxDurationInMins,
                delayInMins = fmtForm.delayInMins,
                advanceInMins = fmtForm.advanceInMins,
                companyMetadata = fmtForm.companyMetadata?.let { LocalCompanyMetadata.mapToOriginal(it) },
                companyCollectionPath = fmtForm.companyCollectionPath,
            )
        }
        
        fun trimmedFields(form: LocalBookedAppointmentMetadata) =
            LocalBookedAppointmentMetadata().apply {
                type = form.type.trim()
                positionInQueue = form.positionInQueue.trim()
                status = form.status.trim()
                approxStartTime = form.approxStartTime
                approxDurationInMins = form.approxDurationInMins
                delayInMins = form.delayInMins
                advanceInMins = form.approxDurationInMins
                companyMetadata = form.companyMetadata
                    ?.let { LocalCompanyMetadata.trimmedFields(it) }
                companyCollectionPath = form.companyCollectionPath?.trim()
            }
    }
}
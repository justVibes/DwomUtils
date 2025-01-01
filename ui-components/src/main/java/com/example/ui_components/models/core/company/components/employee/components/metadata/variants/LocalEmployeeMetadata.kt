package com.example.ui_components.models.core.company.components.employee.components.metadata.variants

import com.example.ui_components.models.client.components.color.variants.LocalCustomColor
import com.example.ui_components.models.core.company.components.book_appointment.variants.LocalBookedAppointment
import com.example.ui_components.models.core.company.components.employee.components.metadata.EmployeeMetadata
import com.example.ui_components.models.core.type_of_worker.components.worker_title.variants.LocalWorkerTitle
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LocalEmployeeMetadata : EmbeddedRealmObject {
    var title: LocalWorkerTitle? = null
    var partnerEmail: String? = null
    var hasAppointments: Boolean = false
    var bookedAppointments: RealmList<LocalBookedAppointment>? = null
    var assignedColor: LocalCustomColor? = null
    var nextAvailable: Long = 0L
    var docPath: String? = null


    companion object {
        fun mapToOriginal(form: LocalEmployeeMetadata) = EmployeeMetadata(
            title = LocalWorkerTitle.mapToOriginal(form.title ?: LocalWorkerTitle()),
            partnerEmail = form.partnerEmail?.trim(),
            hasAppointments = form.hasAppointments,
            bookedAppointments = form.bookedAppointments?.map {
                LocalBookedAppointment.mapToOriginal(it)
            },
            assignedColor = LocalCustomColor.mapToOriginal(
                form.assignedColor ?: LocalCustomColor()
            ),
            nextAvailable = form.nextAvailable,
            docPath = form.docPath?.trim()
        )
    }
}
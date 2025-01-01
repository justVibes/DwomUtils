package com.example.ui_components.models.core.company.components.employee.components.metadata

import com.example.ui_components.models.client.components.color.CustomColor
import com.example.ui_components.models.core.company.components.employee.components.EmployeeTitles
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import com.example.ui_components.models.core.company.components.employee.components.metadata.variants.LocalEmployeeMetadata
import com.example.ui_components.models.core.type_of_worker.components.worker_title.WorkerTitle
import io.realm.kotlin.ext.toRealmList

data class EmployeeMetadata(
    val title: WorkerTitle = WorkerTitle(), /* Use 'EmployeeTitles' to initialize */
    val partnerEmail: String? = null,
    val hasAppointments: Boolean = false,
    val bookedAppointments: List<BookedAppointment>? = if (hasAppointments) emptyList() else null,
    val assignedColor: CustomColor = CustomColor(rand1, rand2, rand3),
    val nextAvailable: Long = 0L,
    val docPath: String? = null
) {
    init {
        if (!hasAppointments && bookedAppointments != null) throw IllegalArgumentException("property: [bookedAppointments] can't be initialized when property: [hasAppointments] = false.")
        if (!EmployeeTitles.values()
                .any { it.fmt.lowercase() == title.formatted.lowercase() && it.abv.lowercase() == title.abv.lowercase() }
        ) throw IllegalArgumentException("property: [title] isn't initialized with the 'EmployeeTitles' enum")
    }

    companion object {
        private val rand1 = (20..235).random()
        private val rand2 = (20..235).random()
        private val rand3 = (20..235).random()
        fun mapToLocal(form: EmployeeMetadata) = LocalEmployeeMetadata().apply {
            title = WorkerTitle.mapToLocal(form.title)
            partnerEmail = form.partnerEmail?.trim()
            hasAppointments = form.hasAppointments
            bookedAppointments =
                form.bookedAppointments?.map { BookedAppointment.mapToLocal(it) }?.toRealmList()
            assignedColor = CustomColor.mapToLocal(form.assignedColor)
            nextAvailable = form.nextAvailable
            docPath = form.docPath?.trim()
        }
    }
}
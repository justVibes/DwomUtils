package com.example.ui_components.models.core.company.components.book_appointment.variants

import com.example.ui_components.models.client.components.service_provider.LocalServiceProvider
import com.example.ui_components.models.client.components.summary.variants.LocalClientSummary
import com.example.ui_components.models.core.company.components.book_appointment.BookedAppointment
import io.realm.kotlin.types.EmbeddedRealmObject

class LocalBookedAppointment : EmbeddedRealmObject {
    var type: String = ""
    var positionInQueue: String = ""
    var status: String = ""
    var approxStartTime: Long = 0L
    var approxDurationInMins: Int = 0
    var delayInMins: Int = 0
    var advanceInMins: Int = 0
    var companyCollectionPath: String? = null
    var serviceProvider: LocalServiceProvider? = null
    var assistantServiceProvider: LocalServiceProvider? = null
    var clientSummary: LocalClientSummary? = null/*This is for local usage*/

    companion object {
        fun mapToOriginal(form: LocalBookedAppointment): BookedAppointment {
            val formattedForm = trimmedFields(form)
            return BookedAppointment(
                type = formattedForm.type,
                status = formattedForm.status,
                approxStartTime = form.approxStartTime,
                approxDurationInMins = form.approxDurationInMins,
                delayInMins = form.delayInMins,
                advanceInMins = form.advanceInMins,
                serviceProvider = formattedForm.serviceProvider?.let {
                    LocalServiceProvider.mapToOriginal(it)
                },
                assistantServiceProvider = formattedForm.assistantServiceProvider?.let {
                    LocalServiceProvider.mapToOriginal(it)
                },
                companyCollectionPath = formattedForm.companyCollectionPath,
                clientSummary = formattedForm.clientSummary?.let {
                    LocalClientSummary.mapToOriginal(it)
                }
            )
        }

        fun trimmedFields(form: LocalBookedAppointment) = LocalBookedAppointment().apply {
            type = form.type.trim()
            status = form.status.trim()
            clientSummary = form.clientSummary?.let { LocalClientSummary.trimmedFields(it) }
            serviceProvider = form.serviceProvider?.let {
                LocalServiceProvider.trimmedFields(it)
            }
            assistantServiceProvider = form.assistantServiceProvider?.let {
                LocalServiceProvider.trimmedFields(it)
            }
            companyCollectionPath = form.companyCollectionPath?.trim()
        }
    }
}
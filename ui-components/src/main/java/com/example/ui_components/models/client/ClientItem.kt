package com.example.ui_components.models.client

import com.example.ui_components.models.client.components.ClientInfo
import com.example.ui_components.models.client.components.ClientNote
import com.example.ui_components.models.client.components.ClientRecord
import com.example.ui_components.models.client.components.ClientVitals
import com.example.ui_components.models.client.components.EmergencyContactInfo
import com.example.ui_components.models.core.user.components.ConnectionKey
import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID


@Serializable
data class ClientItem(
    @Transient val connectionKey: ConnectionKey = ConnectionKey(),
    var clientId: String = "${UUID.randomUUID()}",
    var serviceProviderUid: String = "",
    var serviceProviderName: String = "",
    var authorizedEditors: List<String> = emptyList(),
    var vitals: ClientVitals = ClientVitals(),
    var clientInfo: ClientInfo = ClientInfo(),
    var emergencyContactInfo: EmergencyContactInfo = EmergencyContactInfo(clientId = clientId),
    @Transient var originalDocRef: DocumentReference? = null,
    @Transient var clientCopyDocRefs: List<DocumentReference> = emptyList(),
    @Transient var tempClientCopies: List<ClientItemCopy> = emptyList(), /*This is for local usage.*/
    var tempNotes: List<ClientNote> = emptyList(), /*This is for local usage.*/
    @Transient var noteRefs: List<DocumentReference> = emptyList(),
    var labResults: List<String> = emptyList(),
    var history: List<ClientRecord> = emptyList()
) {
    object MapToNestedObject {
        fun toVitals(from: ClientItem) =
            ClientVitals(
                clientId = from.clientId,
                heartRate = from.vitals.heartRate,
                respiratoryRate = from.vitals.respiratoryRate,
                bloodPressure = from.vitals.bloodPressure,
                bloodOxygen = from.vitals.bloodOxygen,
                bodyTemperatureCel = from.vitals.bodyTemperatureCel,
            )

        fun toClientInfo(from: ClientItem) =
            ClientInfo(
                clientId = from.clientId,
                tagName = from.clientInfo.tagName,
                photoUrl = from.clientInfo.photoUrl,
                firstName = from.clientInfo.firstName,
                lastName = from.clientInfo.lastName,
                sex = from.clientInfo.sex,
                birthDate = from.clientInfo.birthDate,
                birthPlace = from.clientInfo.birthPlace,
                height = from.clientInfo.height,
                weight = from.clientInfo.weight,
                presentAddress = from.clientInfo.presentAddress,
                occupation = from.clientInfo.occupation,
                age = from.clientInfo.age,
                localPhoneNumber = from.clientInfo.localPhoneNumber,
                emailAddress = from.clientInfo.emailAddress,
            )

        fun toEmergencyContact(from: ClientItem) =
            EmergencyContactInfo(
                clientId = from.clientId,
                name = from.emergencyContactInfo.name,
                phoneNumber = from.emergencyContactInfo.phoneNumber,
                email = from.emergencyContactInfo.email,
                presentAddress = from.emergencyContactInfo.presentAddress,
            )

        fun toTempNotes(from: ClientItem) =
            from.tempNotes.map {
                it.apply { clientId = from.clientId }
            }
    }
}





package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.components.EditType
import com.example.ui_components.models.client.components.components.stringComparisonEditType
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class EmergencyContactInfo(
    @PrimaryKey
    var clientId: String = "",
    var name: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var presentAddress: String = "",
) {
    object MapToHighlighted {
        fun from(
            original: EmergencyContactInfo,
            modified: EmergencyContactInfo
        ): HighlightedEmergencyContactInfo {
            return HighlightedEmergencyContactInfo(
                clientId = stringComparisonEditType(original.clientId, modified.clientId),
                name = stringComparisonEditType(original.name, modified.name),
                phoneNumber = stringComparisonEditType(original.phoneNumber, modified.phoneNumber),
                email = stringComparisonEditType(original.email, modified.email),
                presentAddress = stringComparisonEditType(
                    original.presentAddress,
                    modified.presentAddress
                ),
            )
        }
    }
}


data class HighlightedEmergencyContactInfo(
    val clientId: EditType = EditType.NONE,
    val name: EditType = EditType.NONE,
    val phoneNumber: EditType = EditType.NONE,
    val email: EditType = EditType.NONE,
    val presentAddress: EditType = EditType.NONE,
)
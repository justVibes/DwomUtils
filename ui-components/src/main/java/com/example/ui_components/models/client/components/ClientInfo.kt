package com.example.ui_components.models.client.components

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.client.components.components.EditType
import com.example.ui_components.models.client.components.components.stringComparisonEditType
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ClientInfo(
    @PrimaryKey
    var clientId: String = "",
    var tagName: String = "",
    var photoUrl: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var sex: String = "",
    val birthDate: String = "",
    var birthPlace: String = "",
    var height: String = "",
    var weight: String = "",
    var presentAddress: String = "",
    var occupation: String = "",
    var age: Int = 0, /*Generated based on the given birth date*/
    var localPhoneNumber: String = "",
    var emailAddress: String = "",
) {
    object MapToHighlighted {
        fun from(original: ClientInfo, modified: ClientInfo) = HighlightedClientInfo(
            clientId = stringComparisonEditType(original.clientId, modified.clientId),
            tagName = stringComparisonEditType(original.tagName, modified.tagName),
            photoUrl = stringComparisonEditType(original.photoUrl, modified.photoUrl),
            firstName = stringComparisonEditType(original.firstName, modified.firstName),
            lastName = stringComparisonEditType(original.lastName, modified.lastName),
            sex = stringComparisonEditType(original.sex, modified.sex),
            birthDate = stringComparisonEditType(original.birthDate, modified.birthDate),
            birthPlace = stringComparisonEditType(original.birthPlace, modified.birthPlace),
            height = stringComparisonEditType(original.height, modified.height),
            weight = stringComparisonEditType(original.weight, modified.weight),
            presentAddress = stringComparisonEditType(original.presentAddress, modified.presentAddress),
            occupation = stringComparisonEditType(original.occupation, modified.occupation),
            localPhoneNumber = stringComparisonEditType(original.localPhoneNumber, modified.localPhoneNumber),
            emailAddress = stringComparisonEditType(original.emailAddress, modified.emailAddress),
        )
    }
}


data class HighlightedClientInfo(
    var clientId: EditType = EditType.NONE,
    var tagName: EditType = EditType.NONE,
    var photoUrl: EditType = EditType.NONE,
    var firstName: EditType = EditType.NONE,
    var lastName: EditType = EditType.NONE,
    var sex: EditType = EditType.NONE,
    val birthDate: EditType = EditType.NONE,
    var birthPlace: EditType = EditType.NONE,
    var height: EditType = EditType.NONE,
    var weight: EditType = EditType.NONE,
    var presentAddress: EditType = EditType.NONE,
    var occupation: EditType = EditType.NONE,
    var localPhoneNumber: EditType = EditType.NONE,
    var emailAddress: EditType = EditType.NONE,
)


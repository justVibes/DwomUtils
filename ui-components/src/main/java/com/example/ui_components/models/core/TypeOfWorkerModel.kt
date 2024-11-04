package com.example.ui_components.models.core

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.core.establishment.Establishment
import com.google.firebase.firestore.DocumentReference

data class TypeOfWorkerModel(
    var formattedWorkerTitle: String = "",
    var abvWorkerTitle: String = "",
    var endProduct: String = "",
    var typeOfAppointment: String = "",
    var needsEstablishment: Boolean = false,
    var estCategory: String = "",
    var establishments: List<DocumentReference> = emptyList(),
    var tempEstablishments: List<Establishment> = emptyList() /*This is for local usage*/
) {
    object MapToStripped {
        fun from(form: TypeOfWorkerModel) =
            TypeOfWorkerModelStripped(
                formattedWorkerTitle = form.formattedWorkerTitle,
                abvWorkerTitle = form.abvWorkerTitle,
                endProduct = form.endProduct,
                typeOfAppointment = form.typeOfAppointment,
                needsEstablishment = form.needsEstablishment,
                estCategory = form.estCategory,
            )
    }
}

@Entity
data class TypeOfWorkerModelStripped(
    @PrimaryKey
    var formattedWorkerTitle: String = "",
    var abvWorkerTitle: String = "",
    var endProduct: String = "",
    var typeOfAppointment: String = "",
    var needsEstablishment: Boolean = false,
    var estCategory: String = "",
) {
    object MapToOriginal {
        fun from(
            form: TypeOfWorkerModelStripped,
            establishmentsRef: List<DocumentReference>,
            tempEstablishments: List<Establishment>
        ) =
            TypeOfWorkerModel(
                formattedWorkerTitle = form.formattedWorkerTitle,
                abvWorkerTitle = form.abvWorkerTitle,
                endProduct = form.endProduct,
                typeOfAppointment = form.typeOfAppointment,
                needsEstablishment = form.needsEstablishment,
                estCategory = form.estCategory,
                establishments = establishmentsRef,
                tempEstablishments = tempEstablishments
            )
    }
}

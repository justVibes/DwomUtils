package com.example.ui_components.models.core

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.core.company.Company
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class TypeOfWorkerModel(
    var formattedWorkerTitle: String = "",
    var abvWorkerTitle: String = "",
    var endProduct: String = "",
    var typeOfAppointment: String = "",
    var hasEmployer: Boolean = false,
    var companyCategory: String = "",
    var icon: String = "",
    var companySummaryRefs: List<DocumentReference> = emptyList(),
    @Exclude var tempCompanies: List<Company> = emptyList() /*This is for local usage*/
) {
    object MapToStripped {
        fun from(form: TypeOfWorkerModel) =
            TypeOfWorkerModelStripped(
                formattedWorkerTitle = form.formattedWorkerTitle,
                abvWorkerTitle = form.abvWorkerTitle,
                endProduct = form.endProduct,
                typeOfAppointment = form.typeOfAppointment,
                hasEmployer = form.hasEmployer,
                companyCategory = form.companyCategory,
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
    var hasEmployer: Boolean = false,
    var companyCategory: String = "",
) {
    object MapToOriginal {
        fun from(
            form: TypeOfWorkerModelStripped,
            establishmentsRef: List<DocumentReference>,
            tempCompanies: List<Company>
        ) =
            TypeOfWorkerModel(
                formattedWorkerTitle = form.formattedWorkerTitle,
                abvWorkerTitle = form.abvWorkerTitle,
                endProduct = form.endProduct,
                typeOfAppointment = form.typeOfAppointment,
                hasEmployer = form.hasEmployer,
                companyCategory = form.companyCategory,
                companySummaryRefs = establishmentsRef,
                tempCompanies = tempCompanies
            )
    }
}

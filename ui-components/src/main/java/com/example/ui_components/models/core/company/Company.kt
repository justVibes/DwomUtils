package com.example.ui_components.models.core.company

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ui_components.models.core.company.components.CompanySummary
import com.example.ui_components.models.core.company.components.Employee
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import java.util.UUID

data class Company(
    val companyId: String = "${UUID.randomUUID()}",
    val summary: CompanySummary? = null,
    val photoUrl: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val aboutUs: String = "",
    var employees: List<DocumentReference> = emptyList(),
    @Exclude var tempEmployees: List<Employee> = emptyList()/*This is for local usage*/
) {
    object MapToStripped {
        fun from(form: Company) =
            CompanyStripped(
                companyId = form.companyId,
                photoUrl = form.photoUrl,
                name = form.summary!!.name,
                coarseLocation = form.coarseLocation,
                type = form.type,
                category = form.summary.category,
                collectionPath = form.summary.collectionPath
            )
    }
}

@Entity
data class CompanyStripped(
    @PrimaryKey
    val companyId: String = "${UUID.randomUUID()}",
    val photoUrl: String = "",
    val name: String = "",
    val coarseLocation: String = "",
    val type: String = "",
    val category: String = "",
    val collectionPath: String = ""
) {
    object MapToOriginal {
        fun from(
            form: CompanyStripped,
            workers: List<DocumentReference>,
            tempEmployees: List<Employee>
        ) =
            Company(
                companyId = form.companyId,
                photoUrl = form.photoUrl,
                summary = CompanySummary(
                    name = form.name,
                    category = form.category,
                    collectionPath = form.collectionPath
                ),
                coarseLocation = form.coarseLocation,
                type = form.type,
                employees = workers,
                tempEmployees = tempEmployees,
            )
    }
}

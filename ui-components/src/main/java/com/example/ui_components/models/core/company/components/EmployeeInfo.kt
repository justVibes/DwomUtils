package com.example.ui_components.models.core.company.components

import com.google.firebase.firestore.DocumentReference

data class EmployeeInfo(
    var abvEmployeeTitle: String = "",
    var formattedEmployeeTitle: String = "",
    var companySummary: CompanySummary? = null,
    var employeeDocRef: DocumentReference? = null
)
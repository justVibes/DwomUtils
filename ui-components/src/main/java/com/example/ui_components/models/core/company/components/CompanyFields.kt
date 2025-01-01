package com.example.ui_components.models.core.company.components

sealed class CompanyFields(val label: String, val types: List<String>) {
    object Medical : CompanyFields(label = "Medical", types = Types.values().map { it.fmtName }) {
        enum class Types(val fmtName: String) {
            Hospital(Hospital.name),
            MedicalCenter("Medical Center"),
            DoctorOffice("Doctor's Office")
        }
    }
}
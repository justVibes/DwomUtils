package com.example.ui_components.models.core

enum class EmployeeTitles(val fmt: String, val abv: String) {
    Doctor(Doctor.name, "Dr"),
    DoctorAssist("Physician Assistant", "PA"),
    Receptionist(Receptionist.name, "Recpst"),
    Manager(Manager.name, "Mgr")
}
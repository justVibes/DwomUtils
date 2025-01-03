package com.example.ui_components.models.core.company.components

import com.example.ui_components.models.core.worker_title.WorkerTitle

sealed class CompanyFields(val label: String, val types: List<String>) {
    object Medical : CompanyFields(
        label = "Medical",
        types = Types.values().map { it.fmtName }
    ) {
        enum class Types(
            val fmtName: String,
            val titles: List<WorkerTitle> = Titles.values()
                .map { WorkerTitle(fmt = it.fmt, abv = it.abv) }
        ) {
            Hospital(Hospital.name),
            MedicalCenter("Medical Center")
        }

        enum class Titles(val fmt: String, val abv: String) {
            Doctor(Doctor.name, "Dr"),
            PhysAssist("Physician Assistant", "PA"),
            Receptionist(Receptionist.name, "Recpst"),
            Manager(Manager.name, "Mgr")
        }
    }
}
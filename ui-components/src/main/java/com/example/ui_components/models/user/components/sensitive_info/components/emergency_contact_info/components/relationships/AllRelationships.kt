package com.example.ui_components.models.user.components.sensitive_info.components.emergency_contact_info.components.relationships

import androidx.compose.runtime.Immutable

@Immutable
object AllRelationships{
    object Prefixed {
        val step = BaseRelationships.values().filter { it != BaseRelationships.Husband && it != BaseRelationships.Wife }
            .map { "Step-$it" }
        val grand = listOf(
            BaseRelationships.Father,
            BaseRelationships.Mother,
            BaseRelationships.Son,
            BaseRelationships.Daughter
        ).map { "Grand-$it" }
    }

    object Suffixed {
        val inLaw = BaseRelationships.values().filter { it != BaseRelationships.Husband && it != BaseRelationships.Wife }
            .map { "$it-in-law" }
    }

    val values = BaseRelationships.values()
        .map { it.name } + Prefixed.let { Prefixed.step + Prefixed.grand } + Suffixed.inLaw
}
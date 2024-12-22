package com.example.ui_components.ui.text_fields.search_textfield.components

import com.example.ui_components.models.core.search.SearchHint


data class SearchFieldConfig(
    val searchText: String,
    val previousSearches: List<SearchHint>,
    val matchingSearches: List<SearchHint>,
    val onUpdateSearch: (String) -> Unit,
    val onHintClicked: (SearchHint) -> Unit,
    val onClearClicked: () -> Unit,
    val onManageSearchClicked: () -> Unit,
    val onSearchClicked: () -> Unit,
)
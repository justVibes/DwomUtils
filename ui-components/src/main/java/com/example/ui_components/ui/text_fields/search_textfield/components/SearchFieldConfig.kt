package com.bizmap.book_it_business.core.presentation.search_textfield.components

import com.example.ui_components.models.core.SearchHint


data class SearchFieldConfig<T>(
    val searchText: String,
    val previousSearches: List<SearchHint<T>>,
    val matchingSearches: List<SearchHint<T>>,
    val onUpdateSearch: (String) -> Unit,
    val onHintClicked: (SearchHint<T>) -> Unit,
    val onClearClicked: () -> Unit,
    val onManageSearchClicked: () -> Unit,
    val onSearchClicked: () -> Unit,
)
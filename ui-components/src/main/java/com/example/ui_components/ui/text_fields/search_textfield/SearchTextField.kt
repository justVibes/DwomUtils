package com.example.ui_components.ui.text_fields.search_textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.models.core.search.SearchHint
import com.example.ui_components.ui.core.core_logic.WindowSizes

@Composable
fun SearchTextField(
    windowSizeClass: WindowSizeClass,
    height: Dp,
    searchText: String,
    previousSearches: List<SearchHint>,
    matchingSearches: List<SearchHint>,
    onHintClicked: (SearchHint) -> Unit,
    onUpdateSearch: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onManageSearchClicked: () -> Unit,
    onClearClicked: () -> Unit
) {
    val searchHints = if (searchText.isEmpty()) previousSearches else matchingSearches
    val focusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SearchField(
            windowSizeClass = windowSizeClass,
            height = height,
            onClearClicked = onClearClicked,
            searchText = searchText,
            onUpdateSearch = onUpdateSearch,
            onSearchClicked = onSearchClicked,
            focusManager = focusManager,
            searchHints = searchHints,
            onHintClicked = onHintClicked
        )

        //Manage-Search Button
        Icon(
            modifier = Modifier
                .height(height)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurface)
                .clickable { onManageSearchClicked() }
                .padding(10.dp),
            painter = painterResource(R.drawable.ic_manage_search),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
private fun SearchField(
    windowSizeClass: WindowSizeClass,
    height: Dp,
    onClearClicked: () -> Unit,
    searchText: String,
    onUpdateSearch: (String) -> Unit,
    onSearchClicked: () -> Unit,
    focusManager: FocusManager,
    searchHints: List<SearchHint>,
    onHintClicked: (SearchHint) -> Unit
) {
    var areHintsVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(WindowSizes.regularFloatWidth(windowSizeClass) + .25f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .onFocusChanged { areHintsVisible = it.isFocused },
            prefix = {
                Icon(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                )
            },
            suffix = {
                if (searchText.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onClearClicked() },
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                    )
                }
            },
            shape = CircleShape,
            value = searchText,
            onValueChange = onUpdateSearch,
            placeholder = {
                Text(
                    text = "Search Clients...",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedPrefixColor = MaterialTheme.colorScheme.onSurface,
                unfocusedPrefixColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked()
                    focusManager.clearFocus(true)
                }
            )
        )

        if (areHintsVisible) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(10.dp),
            ) {
                searchHints.forEach { hint ->
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_history),
                                contentDescription = null
                            )
                        },
                        text = {
                            Text(hint.hint)
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        onClick = { onHintClicked(hint) }
                    )
                }
            }
        }
    }
}
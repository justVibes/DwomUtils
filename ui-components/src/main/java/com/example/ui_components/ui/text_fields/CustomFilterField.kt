package com.example.ui_components.ui.text_fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.ui.core.core_logic.WindowSizes

@Composable
fun CustomFilterField(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    value: String,
    placeholder: String = "Find a worker...",
    shape: RoundedCornerShape = CircleShape,
    useFilterIcon: Boolean = false,
    hasClearBtn: Boolean = false,
    options: List<String> = emptyList(),
    isOptionsVisible: Boolean = false,
    onShowOptionsClicked: (() -> Unit)? = if (options.isNotEmpty()) {
        {}
    } else null,
    onHideOptions: (() -> Unit)? = if (options.isNotEmpty()) {
        {}
    } else null,
    onOptionClicked: ((String) -> Unit)? = if (options.isNotEmpty()) {
        {}
    } else null,
    onClearClicked: (() -> Unit)? = if (hasClearBtn) {
        {}
    } else null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(WindowSizes.regularFloatWidth(windowSizeClass)),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholder,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        leadingIcon = {
            if(!useFilterIcon){
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(5.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }else{
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(5.dp),
                    painter = painterResource(R.drawable.ic_filter_list),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        trailingIcon = {
            if (hasClearBtn && value.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable { onClearClicked!!() },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else if (options.isNotEmpty() && value.isEmpty()) {
                Column {
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .clickable { onShowOptionsClicked!!() },
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    DropdownMenu(
                        expanded = isOptionsVisible,
                        onDismissRequest = { onHideOptions!!() }
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    onOptionClicked!!(option)
                                }
                            )
                        }
                    }
                }

            }
        },
        singleLine = true,
        shape = shape,
    )
}
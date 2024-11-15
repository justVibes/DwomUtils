package com.example.ui_components.ui.dialogs.view_imported_file_dialog

import android.os.Build
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.ui_components.ui.core.core_ui.CloseBtn
import com.example.ui_components.ui.dialogs.view_imported_file_dialog.components.PdfConfig
import com.example.ui_components.ui.dialogs.view_imported_file_dialog.components.PdfSearchResults
import com.example.ui_components.ui.dialogs.view_imported_file_dialog.components.ui.PdfPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ViewImportedFileAlertDialog(
    modifier: Modifier = Modifier,
    photoUrl: String? = null,
    pdfConfig: PdfConfig? = null,
    onHideDialog: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onHideDialog() },
        confirmButton = {},
        title = {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CloseBtn { onHideDialog() }
                }
                if (Build.VERSION.SDK_INT >= 35 && pdfConfig != null && scale == 1f) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = pdfConfig.searchText,
                        trailingIcon = {
                            if (pdfConfig.searchText.isNotEmpty()) {
                                IconButton(
                                    onClick = { pdfConfig.onClearClicked() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        onValueChange = { newText ->
                            pdfConfig.onSearchTextUpdated(newText)

                            pdfConfig.pdfBitmapConverter.renderer?.let { renderer ->
                                scope.launch(Dispatchers.Default) {
                                    val searchResults = (0..<renderer.pageCount).map { pageNumber ->
                                        renderer.openPage(pageNumber).use { page ->
                                            val results = page.searchText(newText)
                                            val matchedRects = results.map {
                                                it.bounds.first()
                                            }

                                            PdfSearchResults(
                                                pageNumber,
                                                matchedRects
                                            )
                                        }
                                    }
                                    pdfConfig.onSearchResultsUpdated(searchResults)
                                }
                            }
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Search Pdf...",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    )
                }
            }
        },
        text = {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                val state = rememberTransformableState { zoomChange, offsetChange, _ ->
                    scale = (scale * zoomChange).coerceIn(1f, 5f)
                    val extraWidth = (scale - 1) * constraints.maxWidth
                    val extraHeight = (scale - 1) * constraints.maxHeight
                    val maxX = extraWidth / 2
                    val maxY = extraHeight / 2

                    offset = Offset(
                        x = (offset.x + scale * offsetChange.x).coerceIn(-maxX, maxX),
                        y = (offset.y + scale * offsetChange.y).coerceIn(-maxY, maxY),
                    )
                }

                if (pdfConfig != null) {
                    LazyColumn(
                        modifier = Modifier
                            .aspectRatio(.5f / 1f)
                            .fillMaxWidth()
                            .padding(15.dp)
                            .graphicsLayer(
                                scaleX = scale,
                                scaleY = scale,
                                translationX = offset.x,
                                translationY = offset.y
                            )
                            .transformable(state),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(pdfConfig.selectedPdf) { index, page ->
                            PdfPage(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                searchResults = pdfConfig.searchResults.find { it.page == index },
                                page = page
                            )
                        }
                    }
                } else if (photoUrl != null) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = scale,
                                scaleY = scale,
                                translationX = offset.x,
                                translationY = offset.y
                            )
                            .transformable(state)
                        ,
                        contentScale = ContentScale.Fit,
                        model = photoUrl.toUri(),
                        contentDescription = null,
                    )
                }
            }
        },
        shape = MaterialTheme.shapes.extraSmall
    )
}
package com.example.ui_components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_components.core.CustomColor
import com.example.ui_components.dialogs.components.ExportDocumentTypes
import com.example.ui_components.theme.UploadBlue

@Composable
fun ExportAlertDialog(
    isExporting: Boolean,
    selectedDocType: ExportDocumentTypes? = null,
    onDocTypeClicked: (ExportDocumentTypes) -> Unit,
    onExportClicked: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { if (!isExporting) onCancel() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(2) { index ->
                    val btn: Pair<Pair<String, Color>, Pair<Color, Float>> = when (index) {
                        0 -> ("Export" to UploadBlue) to (Color.White to .4f)
                        1 -> ("Cancel" to Color.Transparent) to (MaterialTheme.colorScheme.onSurface.copy(
                            alpha = .5f
                        ) to .25f)

                        else -> ("{47:35}" to Color.Unspecified) to (Color.Unspecified to 1f)
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (btn.first.second == Color.Transparent) btn.second.first else Color.Transparent
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = btn.first.second,
                            contentColor = btn.second.first
                        ),
                        onClick = {
                            when (index) {
                                0 -> onExportClicked()
                                1 -> onCancel()
                            }
                        },
                        enabled = !isExporting
                    ) {
                        if (index != 0) {
                            Text(
                                text = btn.first.first,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }

                        if (index == 0 && isExporting) {
                            Text(
                                text = "Exporting...",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontStyle = FontStyle.Italic
                                )
                            )

                            Spacer(Modifier.width(15.dp))
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp)
                            )
                        } else if (index == 0) {
                            Text(
                                text = btn.first.first,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }
                    }
                    if (index != 1) {
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        },
        title = {
            Text(text = "Export Client Data", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Choose the document type that you want the data to be exported as: "
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val docTypes = listOf(
                        ExportDocumentTypes.PDF,
                        ExportDocumentTypes.WORD,
                        ExportDocumentTypes.EXCEL,
                    )
                    docTypes.forEach { docType ->
                        Box(
                            Modifier
                                .size(90.dp)
                                .clip(MaterialTheme.shapes.small)
                                .background(
                                    if (docType != selectedDocType) CustomColor.cardFadedGray()
                                    else MaterialTheme.colorScheme.onSurface.copy(alpha = .7f)
                                )
                                .clickable { onDocTypeClicked(docType) }
                                .padding(5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(docType.icon),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                        }

                        if(docType != docTypes.last()){
                            Spacer(Modifier.width(10.dp))
                        }
                    }
                }
            }
        }
    )
}




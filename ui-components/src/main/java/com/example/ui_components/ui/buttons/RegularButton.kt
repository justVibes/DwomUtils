package com.example.ui_components.ui.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_components.theme.ConfirmBlue

@Composable
fun RegularButton(
    isTextVisible: Boolean = true,
    text: String,
    icon: ImageVector,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    containerColor: Color = ConfirmBlue,
    contentColor: Color = Color.White,
    onClick: () -> Unit,
) {
    Button(
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape,
        onClick = onClick,
        contentPadding = PaddingValues(10.dp)
    ) {
        if (isTextVisible) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.width(5.dp))
        }
        Icon(imageVector = icon, contentDescription = null)
    }
}
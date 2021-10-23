package cz.rnapps.composables.components.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    text: String,
    isSelected: Boolean,
    onClicked: (Boolean) -> Unit
) {
    val border = if (isSelected) {
        BorderStroke(1.dp, color = MaterialTheme.colors.secondaryVariant)
    } else {
        BorderStroke(1.dp, color = MaterialTheme.colors.onSurface)
    }

    val color = if(isSelected) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.surface
    }

    Surface(
        modifier = Modifier.padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = color,
        border = border
    ) {
        Row(modifier = Modifier.clickable { onClicked(isSelected) }) {
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}
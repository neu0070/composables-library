package cz.rnapps.composables.components.custom

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RadioText(
    text: String,
    selected: Boolean,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        RadioButton(selected = selected, onClick = onClicked)
        Text(text = text)
    }
}
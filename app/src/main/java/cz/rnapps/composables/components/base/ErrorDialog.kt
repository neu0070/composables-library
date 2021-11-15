package cz.rnapps.composables.components.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.rnapps.composables.R

@Composable
fun ErrorDialog(
    errorText: String,
    onDialogDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDialogDismiss,
        confirmButton = {
            Button(onClick = onDialogDismiss) {
                Text(text = stringResource(R.string.button_ok))
            }
        },
        title = {
            Text(text = stringResource(R.string.dialog_title_error))
        },
        text = {
            Text(text = errorText)
        }, modifier = Modifier.padding(horizontal = 8.dp)
    )
}
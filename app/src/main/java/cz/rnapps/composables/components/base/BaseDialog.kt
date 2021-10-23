package cz.rnapps.composables.components.base

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun BaseDialog(
    title: String,
    message: String,
    @StringRes
    primaryButtonTextRes: Int,
    @StringRes
    secondaryButtonTextRes: Int,
    onPrimaryButtonClicked: () -> Unit,
    onSecondaryButtonClicked: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            PrimaryButton(textRes = primaryButtonTextRes) {
                onPrimaryButtonClicked()
            }
        },
        modifier = Modifier.padding(horizontal = 8.dp),
        dismissButton = {
            OutlinedButton(
                onClick = onSecondaryButtonClicked,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = secondaryButtonTextRes))
            }
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        }
    )
}
package cz.rnapps.composables.components.custom

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.rnapps.composables.R
import cz.rnapps.composables.ui.theme.ComposablesTheme

@Composable
fun InfoRow(
    @DrawableRes
    iconRes: Int,
    @StringRes
    titleRes: Int,
    messageText: String
) {
    Column {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "InfoRowIcon"
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column {
                Text(
                    text = stringResource(id = titleRes),
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = messageText,
                    style = MaterialTheme.typography.body2
                )
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}

@Preview("LIGHT_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview("DARK_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewInfoRow() {
    ComposablesTheme {
        InfoRow(
            iconRes = R.drawable.ic_launcher_foreground,
            titleRes = R.string.title_text,
            messageText = stringResource(id = R.string.subtitle_text)
        )
    }
}
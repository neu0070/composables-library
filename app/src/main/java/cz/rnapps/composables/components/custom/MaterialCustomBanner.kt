package cz.rnapps.composables.components.custom

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.rnapps.composables.R
import cz.rnapps.composables.ui.theme.ComposablesTheme

@Composable
fun MaterialCustomBanner(
    @StringRes
    primaryButtonTextRes: Int,
    @StringRes
    secondaryButtonTextRes: Int,
    @DrawableRes
    iconRes: Int,
    messageText: String,
    onPrimaryButton: () -> Unit,
    onSecondaryButton: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "BannerIcon",
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colors.onSurface
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = messageText, modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = {
                    onPrimaryButton()
                }) {
                    Text(text = stringResource(id = primaryButtonTextRes))
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = {
                    onSecondaryButton()
                }) {
                    Text(text = stringResource(id = secondaryButtonTextRes))
                }
            }
        }
    }
}

@Preview("LIGHT_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
@Preview("DARK_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun PreviewMaterialCustomBanner() {
    ComposablesTheme {
        MaterialCustomBanner(
            onPrimaryButton = {

            },
            onSecondaryButton = {

            },
            iconRes = R.drawable.ic_launcher_foreground,
            primaryButtonTextRes = R.string.title_text,
            secondaryButtonTextRes = R.string.title_text,
            messageText = "SOME REALLY LONG TEST BLABLABLABLALBALBBLALLBBALBLBALBLBALLBALB"
        )
    }
}
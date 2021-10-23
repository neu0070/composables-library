package cz.rnapps.composables.components.custom

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.rnapps.composables.R
import cz.rnapps.composables.ui.theme.ComposablesTheme

@Composable
fun CustomBanner(
    @StringRes
    titleRes: Int,
    @StringRes
    subTitleRes: Int,
    @DrawableRes
    painterRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Row {
            Icon(
                painter = painterResource(id = painterRes),
                contentDescription = "CustomBannerIcon",
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(8.dp)
            )

            Column(
                Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp),
                    text = stringResource(id = titleRes),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 8.dp),
                    text = stringResource(id = subTitleRes),
                    style = MaterialTheme.typography.subtitle1.copy(),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }
    }
}

data class CustomBannerData(
    @StringRes
    val titleRes: Int,
    @StringRes
    val subTitleRes: Int,
    @DrawableRes
    val iconRes: Int,
    val modifier: Modifier
)

@Preview("LIGHT_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
@Preview("DARK_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun PreviewCustomBanner() {
    ComposablesTheme {
        CustomBanner(
            titleRes = R.string.title_text,
            subTitleRes = R.string.subtitle_text,
            painterRes = R.drawable.ic_launcher_foreground
        )
    }
}
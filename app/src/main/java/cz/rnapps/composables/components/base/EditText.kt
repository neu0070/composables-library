package cz.rnapps.composables.components.base

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.rnapps.composables.R
import cz.rnapps.composables.ui.theme.ComposablesTheme

@Composable
fun EditText(
    data: EditTextData
) {
    Box(modifier = Modifier
        .padding(bottom = 8.dp)
        .clickable {
            data.onClicked?.invoke()
        }) {
        OutlinedTextField(
            value = data.value,
            onValueChange = { changedValue ->
                if (!data.isReadOnly) {
                    data.onValueChanged?.invoke(changedValue)
                }
            },
            readOnly = data.isReadOnly,
            trailingIcon = {
                if (data.trailingIconRes != null) {
                    Icon(
                        painter = painterResource(id = data.trailingIconRes),
                        contentDescription = "TextFieldTrailingIcon",
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier.clickable {
                            data.onIconClicked?.invoke()
                        }
                    )
                }
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onBackground
            ),
            label = {
                Text(text = stringResource(id = data.labelRes))
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    data.onClicked?.invoke()
                },
            singleLine = true,
            visualTransformation = data.type.visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = data.type.keyBoardType
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
@Preview("DARK_MODE", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewEditText() {
    ComposablesTheme {
        EditText(
            EditTextData(
                "Value",
                R.string.label_text,
                R.drawable.ic_launcher_foreground,
                isReadOnly = true
            )
        )
    }
}

data class EditTextData(
    val value: String = "",
    @StringRes
    val labelRes: Int,
    @DrawableRes
    val trailingIconRes: Int? = null,
    val isReadOnly: Boolean = false,
    val onValueChanged: ((String) -> Unit)? = null,
    val onClicked: (() -> Unit)? = null,
    val onIconClicked: (() -> Unit)? = null,
    val type: EditTextType = EditTextType.Text
)

enum class EditTextType(
    val keyBoardType: KeyboardType,
    val visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Password(
        KeyboardType.Password,
        PasswordVisualTransformation()
    ),

    Email(
        KeyboardType.Email
    ),

    Number(
        KeyboardType.Number
    ),

    Text(
        KeyboardType.Text
    )

    ;
}
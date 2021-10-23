package cz.rnapps.composables.components.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import cz.rnapps.composables.components.base.EditText
import cz.rnapps.composables.components.base.EditTextData

@Composable
fun ListOfEditTextItems(data: List<EditTextData>) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        data.forEach {
            EditText(it)
        }
    }
}
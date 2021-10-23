package cz.rnapps.composables.components.custom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDialog(
    isVisible: Boolean,
    sheetContent: @Composable () -> Unit,
    mainContent: @Composable () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    if (isVisible) {
        LaunchedEffect(key1 = "Test", block = {
            scope.launch {
                modalBottomSheetState.show()
            }
        })
    } else {
        LaunchedEffect(key1 = "Test2", block = {
            scope.launch {
                modalBottomSheetState.hide()
            }
        })
    }

    ModalBottomSheetLayout(
        sheetContent = {
            Box(modifier = Modifier.padding(16.dp)) {
                sheetContent()
            }
        },
        sheetState = modalBottomSheetState
    ) {
        mainContent()
    }
}
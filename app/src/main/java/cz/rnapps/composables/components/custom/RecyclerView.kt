package cz.rnapps.composables.components.custom

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> RecyclerView(
    data: List<T>,
    customBannerData: CustomBannerData,
    onRow: @Composable (T) -> Unit,
    onSwipeToDelete: ((T) -> Unit)? = null,
    onSwipeDirectionRight: ((T) -> Unit)? = null,
    onItemClicked: ((T) -> Unit)? = null
) {
    if (data.isEmpty()) {
        CustomBanner(
            titleRes = customBannerData.titleRes,
            subTitleRes = customBannerData.subTitleRes,
            modifier = customBannerData.modifier,
            painterRes = customBannerData.iconRes
        )
    } else {
        LazyColumn(Modifier.fillMaxSize()) {
            items(data) { item ->
                var unread by remember { mutableStateOf(false) }
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd) unread = !unread
                        it != DismissValue.DismissedToEnd
                    }
                )
                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    onSwipeToDelete?.invoke(item)
                }
                if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                    onSwipeDirectionRight?.invoke(item)
                }
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier.padding(vertical = 4.dp),
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { direction ->
                        FractionalThreshold(if (direction == DismissDirection.StartToEnd) CONST_0_25 else CONST_0_5)
                    },
                    background = {
                        val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                DismissValue.Default -> Color.LightGray
                                DismissValue.DismissedToEnd -> Color.Green
                                DismissValue.DismissedToStart -> Color.Red
                            }
                        )
                        val alignment = when (direction) {
                            DismissDirection.StartToEnd -> Alignment.CenterStart
                            DismissDirection.EndToStart -> Alignment.CenterEnd
                        }
                        val icon = when (direction) {
                            DismissDirection.StartToEnd -> Icons.Rounded.Done
                            DismissDirection.EndToStart -> Icons.Rounded.Delete
                        }
                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == DismissValue.Default) CONST_0_75 else CONST_1
                        )

                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                icon,
                                contentDescription = "Localized description",
                                modifier = Modifier.scale(scale)
                            )
                        }
                    },
                    dismissContent = {
                        Card(
                            modifier = Modifier.clickable {
                                onItemClicked?.invoke(item)
                            },
                            elevation = animateDpAsState(
                                if (dismissState.dismissDirection != null) 4.dp else 0.dp
                            ).value
                        ) {
                            onRow(item)
                        }
                    }
                )
            }
        }
    }
}

private const val CONST_0_25 = 0.25f
private const val CONST_0_5 = 0.5f
private const val CONST_0_75 = 0.75f
private const val CONST_1 = 1f
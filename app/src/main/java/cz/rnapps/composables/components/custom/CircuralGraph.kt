package cz.rnapps.composables.components.custom

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.rnapps.composables.ui.theme.ComposablesTheme
import java.math.BigDecimal

@Composable
fun CircularGraph(
    percentage: BigDecimal,
    text: String,
    radius: Dp = 100.dp,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 20.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) {
            percentage.toFloat()
        } else {
            0f
        },
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true, block = {
        animationPlayed = true
    })

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f)
            .padding(20.dp)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f), onDraw = {
            drawCircle(
                Color.LightGray,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color,
                startAngle = -90f,
                sweepAngle = CONST_360_DEGREES * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        })
        Text(text = text, style = MaterialTheme.typography.subtitle2, color = color)
    }
}

private const val CONST_360_DEGREES = 360

@Preview("LIGHT_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
@Preview("DARK_MODE", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun PreviewCircularGraph() {
    ComposablesTheme {
        CircularGraph(percentage = BigDecimal.ONE, text = "8 000 000.99")
    }
}
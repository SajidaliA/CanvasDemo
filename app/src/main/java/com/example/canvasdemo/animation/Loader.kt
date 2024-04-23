package com.example.canvasdemo.animation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpinnerLoader(
    modifier: Modifier = Modifier,
    sections: Int = 12,
    color: Color = Color.White,
    sectionLength: Dp = 6.dp,
    sectionWidth: Dp = 6.dp
) {

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val sectionOffset by infiniteTransition.animateValue(
        initialValue = 0,
        targetValue = sections,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = 1000
            }, repeatMode = RepeatMode.Restart
        ),
        label = "angle_animation"
    )
    Canvas(modifier = modifier) {

        val radius = size.height / 2
        val angle = 360f / sections
        val alpha = 1f /sections

        rotate(sectionOffset * angle) {
            for (i in 1 .. sections){
                rotate(angle * i) {
                    drawLine(
                        color = color.copy(alpha = alpha * i),
                        strokeWidth = sectionWidth.toPx(),
                        start = Offset(
                            x = radius,
                            y = sectionLength.toPx()
                        ),
                        end = Offset(
                            x = radius,
                            y = sectionLength.toPx() * 2
                        ),
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }
}
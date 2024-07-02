package org.example.project.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.round

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.dragOffsetHandler(
    threshold:Float = 200f,
    onDismiss:() -> Unit
):Modifier {
    var offsetX by remember { mutableStateOf(0f)}
    var offsetY by remember { mutableStateOf(0f)}
    val animatedOffset = animateOffsetAsState(
        targetValue = Offset(offsetX,offsetY),
        animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
    )

    return Modifier.offset { animatedOffset.value.round() }.pointerInput(Unit) {
        detectDragGestures(onDragEnd = {
            if (offsetX > threshold || offsetY > threshold) onDismiss()
            offsetX = 0f
            offsetY = 0f
        }) { change, dragAmount ->
            offsetX += change.position.x - change.previousPosition.x
            offsetY += change.position.y - change.previousPosition.y
        }
    }
}
package com.ananananzhuo.customlayout

import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource

/**
 * author  :mayong
 * function:
 * date    :2021/8/6
 **/


@Composable
fun customLayout() {

    CustomColumn {
        Text(text = "hahahahahahha")
        Image(painter = painterResource(id = R.drawable.apple), contentDescription = "")
    }
}

@Composable
private fun CustomColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measuable, constraint ->
        val placeable = measuable.map {
            it.measure(constraint)
        }
        layout(constraint.maxWidth, constraint.maxHeight) {
            var yPosition = 0
            placeable.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)
                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}
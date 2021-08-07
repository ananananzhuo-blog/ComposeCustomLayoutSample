package com.ananananzhuo.customlayout

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.ananananzhuo.composelib.buildTopBar

/**
 * author  :mayong
 * function:
 * date    :2021/8/7
 **/

@Composable
fun starLayout() {
    val size = 30.dp
    Column {
        buildTopBar(title = "摩天轮")
        startLayoutReal {
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
        }
    }

}

@Composable
private fun startLayoutReal(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val transition = rememberInfiniteTransition()
    val currentDegree by transition.animateFloat(
        initialValue = 0f, targetValue = 2 * Math.PI.toFloat(), animationSpec = infiniteRepeatable(
            animation = tween(10 * 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    startAnimLayoutReal(modifier = modifier, currentDegree.toDouble(), content = content)
}

@Composable
private fun startAnimLayoutReal(
    modifier: Modifier = Modifier,
    degree: Double,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measurable, constraint ->
        val placeable = measurable.map {
            it.measure(constraint)
        }
        val perDegree = 2 * Math.PI / placeable.size//两个子view之间的弧度间隔
        val width = 300//摆放大圆的半径
        val startX = 450//圆心x坐标
        val startY = 450//圆心y坐标
        var currentDegree = degree//当前所属的弧度
        layout(constraint.maxWidth, constraint.maxHeight) {
            placeable.forEach {
                val x = Math.sin(currentDegree) * width + startY
                val y = Math.cos(currentDegree) * width + startX
                it.placeRelative(x = x.toInt(), y = y.toInt())//根据控件当前弧度将控件摆放在大圆的对应位置上
                currentDegree += perDegree.toFloat()//弧度加一，摆放下一个子布局
            }
        }
    }
}
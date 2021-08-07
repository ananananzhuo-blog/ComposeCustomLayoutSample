关注公众号学习更多知识

![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)




## 先看最终效果效果

![](https://files.mdnice.com/user/15648/30bb8c1e-fd85-4a58-a0e0-60d89aadb702.gif)


## Layout作用
自定义布局使用`Layout`实现，`Layout`可以布局多个可组合项。我们可以通过`Layout`实现各种复杂的自定义布局。`Column`和`Row`都是通过`Layout`实现的



## Layout api讲解

`下面的代码摘抄自官方，实现了一个Column的效果`
```kotlin
@Composable
fun MyBasicColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

      //placeables是测量完的子view列表
       val placeables = measurables.map { measurable ->
            measurable.measure(constraints)//根据父布局约束测量
        }
        layout(constraints.maxWidth, constraints.maxHeight) {//maxWidth就是父布局的最大宽度，layout函数是用来布局摆放子布局的
            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)//根据相对位置摆放子view
                yPosition += placeable.height
            }
        }
    }
}
```
- `content:` 被传递给子项的view函数
- `measurables:` 所有可被测量的布局列表
- `constraints:` 来自于父布局的约束条件

## 实现自定义布局

本例中我们实现了这样一个效果，传入n个子布局，然后将这个子布局使用自定义布局`Layout`实现原型摆放的效果。代码如下

```kotlin

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
```

## 给布局加上摩天轮的旋转效果

我们使用`rememberInfiniteTransition`实现动画无限执行的效果，这样多个子view围绕之圆心进行无限旋转，看起来就像一个大摩天轮

```kotlin

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
```
package com.ananananzhuo.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ananananzhuo.composelib.CommonUtils
import com.ananananzhuo.composelib.ListView
import com.ananananzhuo.composelib.bean.ItemData
import com.ananananzhuo.composelib.buildTopBar
import com.ananananzhuo.composelib.constants.PAGE1
import com.ananananzhuo.customlayout.ui.theme.ComposeCustomLayoutSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CommonUtils.setStatusBarColor(this)
        setContent {
            ComposeCustomLayoutSampleTheme(darkTheme = false) {
//                TopAppBar(backgroundColor = Color.Yellow,contentColor = Color.White) {
//
//                }
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
private fun Greeting() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = HOMEPAGE) {
        composable(HOMEPAGE) {
            mainPage(controller)
        }
        composable(CUSTOMLAYOUT_PAGE) {
            customLayout()
        }
        composable(PAGE1) {
            starLayout()
        }
    }
}

@Composable
private fun mainPage(controller: NavHostController) {
    val datas = remember {
        mutableStateListOf(
            ItemData(title = "自定义一个Column", tag = CUSTOMLAYOUT_PAGE),
            ItemData(title = "自定义六角星布局", tag = PAGE1)
        )
    }
    Column {
        buildTopBar()
        ListView(datas = datas, click = { data, _, _ ->
            controller.navigate(data.tag)
        })
    }
}
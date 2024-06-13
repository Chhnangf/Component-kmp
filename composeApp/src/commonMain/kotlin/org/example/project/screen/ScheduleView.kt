package org.example.project.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.AllIcons
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.AlertTriangle
import compose.icons.evaicons.outline.Bulb
import compose.icons.evaicons.outline.Headphones
import compose.icons.evaicons.outline.Info
import compose.icons.evaicons.outline.Monitor
import compose.icons.evaicons.outline.Radio
import kotlinx.coroutines.launch

enum class AppPages(val title: String, val icon: ImageVector) {
    PAGE_ONE("游戏", EvaIcons.Outline.Monitor),
    PAGE_TWO("发现", EvaIcons.Outline.Radio),
    PAGE_THEE("音乐", EvaIcons.Outline.Headphones)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScheduleView() {

    val pagerState = rememberPagerState(pageCount = { AppPages.entries.size }, initialPage = AppPages.PAGE_TWO.ordinal)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // 此处顶部栏样式，可扩展为 -> 自定义+TabRow+自定义
            TabRow(selectedTabIndex = pagerState.currentPage) {
                AppPages.entries.forEachIndexed { index, page ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(page.title, fontSize = 12.sp) },
                        icon = {
                            Icon(
                                imageVector = page.icon,
                                "pageIcon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState) { page ->
                when (AppPages.entries[page]) {
                    AppPages.PAGE_ONE -> PageOneContent()
                    AppPages.PAGE_TWO -> PageTwoContent()
                    AppPages.PAGE_THEE -> PageThrContent()
                }
            }
        }
    }

}

@Composable
fun PageOneContent() {
    // Your content for Page One here
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
        Text("This is Page One", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun PageTwoContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Text("This is Page Two", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun PageThrContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        Text("This is Page Thr", modifier = Modifier.align(Alignment.Center))
    }
}



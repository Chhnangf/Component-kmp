package org.example.project.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import compose.icons.AllIcons
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.AlertTriangle
import compose.icons.evaicons.outline.Info
import kotlinx.coroutines.launch

enum class AppPages(val title: String, val icon: ImageVector) {
    PAGE_ONE("Page One", EvaIcons.Outline.Info),
    PAGE_TWO("Page Two", EvaIcons.Outline.AlertTriangle)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScheduleView() {

    val pagerState = rememberPagerState(pageCount = { AppPages.entries.size })
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
                        text = { Text(page.title) },
                        icon = {
                            Icon(page.icon, "pageIcon")
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState) { page ->
                when (AppPages.entries[page]) {
                    AppPages.PAGE_ONE -> PageOneContent()
                    AppPages.PAGE_TWO -> PageTwoContent()
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




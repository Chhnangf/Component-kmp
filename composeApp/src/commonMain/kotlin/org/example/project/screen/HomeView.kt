package org.example.project.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Headphones
import compose.icons.evaicons.outline.Monitor
import compose.icons.evaicons.outline.Radio
import kotlinx.coroutines.launch

import org.example.project.data.navigation.Routes

enum class AppPages(val title: String, val icon: ImageVector) {
    PAGE_ONE("游戏", EvaIcons.Outline.Monitor),
    PAGE_TWO("发现", EvaIcons.Outline.Radio),
    PAGE_THEE("音乐", EvaIcons.Outline.Headphones)
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {

    val pagerState = rememberPagerState(pageCount = { AppPages.entries.size }, initialPage = AppPages.PAGE_TWO.ordinal)
    val coroutineScope = rememberCoroutineScope()

    androidx.compose.material3.Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // 此处顶部栏样式，可扩展为 -> 自定义+TabRow+自定义
            androidx.compose.material3.TabRow(selectedTabIndex = pagerState.currentPage) {
                AppPages.entries.forEachIndexed { index, page ->
                    androidx.compose.material3.Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { androidx.compose.material3.Text(page.title, fontSize = 12.sp) },
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
fun TabContent(tab: Routes.TabRoute) {
    // 根据tab展示对应内容，这里仅为示例，实际应映射到具体屏幕或内容
    Text(text = "Content for ${tab.name}")
}
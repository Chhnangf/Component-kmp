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
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Headphones
import compose.icons.evaicons.fill.Monitor
import compose.icons.evaicons.fill.Radio
import compose.icons.evaicons.outline.Headphones
import compose.icons.evaicons.outline.Monitor
import compose.icons.evaicons.outline.Radio
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.example.project.data.Lottie_Chicken
import org.example.project.data.navigation.Routes


enum class AppPages(val title: String, val image: ImageVector, val selectImage: ImageVector) {
    PAGE_ONE("游戏", EvaIcons.Outline.Monitor, EvaIcons.Fill.Monitor),
    PAGE_TWO("发现", EvaIcons.Outline.Radio, EvaIcons.Fill.Radio),
    PAGE_THEE("音乐", EvaIcons.Outline.Headphones, EvaIcons.Fill.Headphones)
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
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { androidx.compose.material3.Text(page.title, fontSize = 12.sp) },
                        icon = {
                            Icon(
                                imageVector = if (pagerState.currentPage == index) page.selectImage else page.image,
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("This is Page One")

        }

    }
}

@Composable
fun PageTwoContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("This is Page Two")
            Loader()
        }
    }
}

@Composable
fun PageThrContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        androidx.compose.material3.Text("This is Page Thr", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun Loader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(Lottie_Chicken))
    val progress by animateLottieCompositionAsState(composition)
//    LottieAnimation(
//        composition = composition,
//        progress = {progress},
//    )
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}





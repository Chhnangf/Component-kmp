package org.example.project.screen.bottomScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.example.project.data.PhotoScreenModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import kotlinx.coroutines.launch
import org.example.project.data.Lottie_Chicken
import org.example.project.data.navigation.Routes
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.example.project.data.PhotoObject
import org.example.project.data.SharedStateManager
import org.example.project.data.navigation.AppPages
import org.example.project.screen.DetailScreen



data object HomeScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val screenModel: PhotoScreenModel = getScreenModel()

        val pagerState = rememberPagerState(
            pageCount = { AppPages.entries.size },
            initialPage = AppPages.PAGE_TWO.ordinal
        )
        val coroutineScope = rememberCoroutineScope()

        Surface {
            Column {

                Box(
                    modifier = Modifier.fillMaxSize().weight(1f)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { paddingValues ->
                        Column(modifier = Modifier.padding(paddingValues)) {
                            // 此处顶部栏样式，可扩展为 -> 自定义+TabRow+自定义
                            androidx.compose.material3.TabRow(selectedTabIndex = pagerState.currentPage) {
                                AppPages.entries.forEachIndexed { index, page ->
                                    Tab(
                                        selected = pagerState.currentPage == index,
                                        onClick = {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(
                                                    index
                                                )
                                            }
                                        },
                                        text = {
                                            androidx.compose.material3.Text(
                                                page.title,
                                                fontSize = 12.sp
                                            )
                                        },
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
                                    AppPages.PAGE_ONE -> PageOneContent(screenModel)
                                    AppPages.PAGE_TWO -> PageTwoContent()
                                    AppPages.PAGE_THEE -> PageThrContent(screenModel)
                                }
                            }
                        }
                    }
                }

                NavationBar()
            }

        }
    }
}

@Composable
fun NavationBar() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp, 6.dp, 20.dp, 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Routes.BarRoute.entries.forEach { route ->
                Column(
                    modifier = Modifier.clickable(
                        onClick = {
                            println("\nbefore click rout -> ${route.description}, ${SharedStateManager.currentTab.value}")
                            SharedStateManager.update(route)
                            println("\nafter click rout -> ${route.description}, ${SharedStateManager.currentTab.value}")
                        },
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    println("icon state -> $route, ${SharedStateManager.currentTab.value}")
                    androidx.compose.material.Icon(
                        imageVector = if (route == SharedStateManager.currentTab.value) route.selectImage else route.imageVector,
                        contentDescription = route.description,
                        tint = if (route == SharedStateManager.currentTab.value) Color.Blue else Color.Black
                    )

                    androidx.compose.material3.Text(
                        text = route.description,
                        fontSize = 11.sp,
                        color = if (route == SharedStateManager.currentTab.value) Color.Blue else Color.Black
                    )

                }

            }
        }
    }
}

@Composable
fun PageOneContent(screenModel: PhotoScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val objects by screenModel.objects.collectAsState()
    val objectList = PhotoObject(
        127,
        "Task 4",
        "feedback",
        "??",
        "0*0",
        "null",
        "null",
        "https://images.metmuseum.org/CRDImages/ep/original/DT1567.jpg",
        "https://images.metmuseum.org/CRDImages/ep/web-additional/DT1567.jpg",
        "null",
        "null",
        "2024-6-27"
    )
    // Your content for Page One here
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("This is Page One")
            Button(
                onClick = {
                    screenModel.addPhotoObjects(listOf(objectList))
                }
            ) {
                Text("POST")
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageThrContent(screenModel: PhotoScreenModel) {

    val navigator = LocalNavigator.currentOrThrow
    val objects by screenModel.objects.collectAsState()
    println(objects)

    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        screenModel.refresh()
        pullToRefreshState.endRefresh()
    }


    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        AnimatedContent(objects.isNotEmpty()) { objectsAvailable ->
            if (objectsAvailable) {
                ObjectGrid(
                    objects = objects,
                    onObjectClick = { objectId ->
                        navigator.push(DetailScreen(objectId))
                    }
                )
            } else {
                EmptyScreenContent(Modifier.fillMaxSize())
            }
            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun ObjectGrid(
    objects: List<PhotoObject>,
    onObjectClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(objects, key = { it.objectID }) { obj ->
            ObjectFrame(
                obj = obj,
                onClick = { onObjectClick(obj.objectID) }
            )
        }
    }
}

@Composable
fun ObjectFrame(
    obj: PhotoObject,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(6.dp)
            .clickable { onClick() }
    ) {
        KamelImage(
            resource = asyncPainterResource(data = obj.primaryImageSmall),
            contentDescription = obj.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.LightGray)
        )

        Spacer(Modifier.height(2.dp))

        Text(obj.title)
        Text(obj.artistDisplayName)
        Text(obj.objectDate)
    }
}

@Composable
fun Loader() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.JsonString(
            Lottie_Chicken
        )
    )
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

@Composable
fun EmptyScreenContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("没有数据！请检查网络")
    }
}

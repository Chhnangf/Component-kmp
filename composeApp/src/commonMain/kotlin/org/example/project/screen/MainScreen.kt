package org.example.project.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.coroutines.launch
import org.example.project.data.PhotoScreenModel
import org.example.project.data.SharedStateManager
import org.example.project.data.navigation.Routes
import org.example.project.data.navigation.ScreenType


object MainScreen : Screen {

    @Composable
    override fun Content() {

        val (currentScreen, setCurrentScreen) = remember { mutableStateOf(ScreenType.HOME_SCREEN) }
        println("remember { mutableStateOf(ScreenType.HOME_SCREEN) } -> $currentScreen, $setCurrentScreen")
        Surface {
            Column(Modifier.background(MaterialTheme.colorScheme.background)) {
                // 内容区域，根据currentScreen显示不同的页面
                ContentScreen(currentScreen)
            }
            Box(
                Modifier.fillMaxSize().padding(bottom = 10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                BottomBar(currentScreen, setCurrentScreen)

            }
        }
    }

    private @Composable
    fun ContentScreen(currentScreen: ScreenType) {
        val screenModel: PhotoScreenModel = getScreenModel()

        when (currentScreen) {
            ScreenType.HOME_SCREEN -> HomeContent(screenModel)
            ScreenType.CHAT_SCREEN -> ChatContent()
            ScreenType.PUSH_SCREEN -> PushContent()
            ScreenType.SET_SCREEN -> SettingsContent()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(screenModel: PhotoScreenModel) {
    // 这里是HomeScreen页面的内容
//    Text("Home Screen Content")
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
                        TabRow(selectedTabIndex = pagerState.currentPage) {
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
                                        Text(
                                            page.title,
                                            fontSize = 12.sp
                                        )
                                    },
                                    icon = {
                                        androidx.compose.material3.Icon(
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

        }

    }
}

@Composable
fun BottomBar(currentScreen: ScreenType, setCurrentScreen: (ScreenType) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 20.dp, vertical = 6.dp) // 外部padding
            .shadow(4.dp) // 在Box上添加阴影
            .clip(RoundedCornerShape(50)) // 使用Box裁剪形状
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface) // 背景色
                .padding(horizontal = 20.dp, vertical = 6.dp), // 外部padding
            shape = RoundedCornerShape(50) // 设置圆角形状
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp), // 内部padding，防止按钮紧挨着边缘
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScreenType.entries.forEach { screenType ->
                    val isSelected = currentScreen == screenType
                    BottomBarItem(
                        selected = isSelected,
                        icon = if (isSelected) screenType.selectedIcon else screenType.unselectedIcon,
                        text = screenType.buttonName
                    ) { setCurrentScreen(screenType) }
                }
            }
        }
    }
}

@Composable
fun BottomBarItem(selected: Boolean, icon: ImageVector, text: String, onClick: () -> Unit) {

    val size by animateDpAsState(targetValue = if (selected) 22.dp else 18.dp) // 动画大小
    val iconColor by animateColorAsState(if (selected) MaterialTheme.colorScheme.primary else Color.Gray)
    val textColor by animateColorAsState(if (selected) MaterialTheme.colorScheme.primary else Color.Gray)
    // 定义基础的TextStyle，使用MaterialTheme.typography.body1作为起始点
    val baseTextStyle = MaterialTheme.typography.bodySmall

    // 根据selected的值来决定字体大小
    val textStyle = baseTextStyle.copy(
        fontSize = if (selected) 14.sp else 12.sp
    )
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .animateContentSize() // 添加动画效果
            .clickable(
                interactionSource = interactionSource,
                indication = null, // 可以自定义点击反馈效果
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = iconColor,
            modifier = Modifier.size(size) // 使用动画大小
        )
        Text(
            text = text,
            color = textColor,
            style = textStyle
        )
    }
    //onClick.invoke() // 这里调用onClick，以便在点击时更新状态
}


@Composable
fun ChatContent() {
    // 这里是ChatScreen页面的内容
    Text("Chat Screen Content")
}

@Composable
fun PushContent() {
    // 这里是PushScreen页面的内容
    Text("Push Screen Content")
}

@Composable
fun SettingsContent() {
    // 这里是SettingsScreen页面的内容
    Text("Settings Screen Content")
}
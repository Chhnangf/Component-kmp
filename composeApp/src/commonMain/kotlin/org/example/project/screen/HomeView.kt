package org.example.project.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import org.example.project.data.navigation.Routes

@Composable
fun HomeView() {

    val currentTab = remember { mutableStateOf(Routes.TabRoute.Game_Screen) } // 默认显示第二个Tab
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentTab.value.title) },
                navigationIcon = {
                    // 如果需要，可以在这里添加返回按钮等
                },
                actions = {
                    // 可以在这里添加更多操作按钮
                }
            )
        },
        content = {
            TabContent(currentTab.value)
        }
    )
    // 直接在 TabRow 中使用枚举值
    TabRow(selectedTabIndex = currentTab.value.ordinal) {
        Routes.TabRoute.entries.forEach { tab ->
            Tab(
                text = { Text(tab.title) },
                selected = currentTab.value == tab,
                onClick = { currentTab.value = tab }
            )
        }
    }
}

@Composable
fun TabContent(tab: Routes.TabRoute) {
    // 根据tab展示对应内容，这里仅为示例，实际应映射到具体屏幕或内容
    Text(text = "Content for ${tab.name}")
}
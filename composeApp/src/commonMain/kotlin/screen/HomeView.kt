package screen
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import compoment.navigation.Routes

@Composable
fun HomeView() {
    val tabs = listOf(
        Routes.TabRoute.Tab1,
        Routes.TabRoute.Tab2,
        Routes.TabRoute.Tab3
    )
    val currentTab = remember { mutableStateOf(tabs[1]) } // 默认显示第二个Tab

    Scaffold(
        topBar = {
        TopAppBar(
            title = { Text(currentTab.value.name) },
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
    // 简化的Tab切换逻辑，实际应用中可能需要更复杂的处理
    TabRow(selectedTabIndex = tabs.indexOf(currentTab.value)) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.name) },
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
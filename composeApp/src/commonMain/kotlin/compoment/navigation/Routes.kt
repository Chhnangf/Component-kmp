package compoment.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.LocalNavigator
import compoment.data.SharedStateManager
import screen.HomeView
import screen.PublishScreen
import screen.ScheduleView
import screen.SettingView
import screen.StatisticsView

class Routes {
    enum class BarRoute {
        Home_Screen {
            override val imageVector: ImageVector = Icons.Default.Home
            override val description: String = "首页"
            // 内部枚举表示首页的顶部Tab

            @Composable
            override fun target() {
                HomeView()
            }
        },

        schedule_Screen {
            override val imageVector: ImageVector = Icons.Rounded.Edit
            override val description: String = "日程"
            @Composable
            override fun target() {
                ScheduleView()
            }
        },

        publish_Screen {
            override val imageVector: ImageVector = Icons.Rounded.MoreVert
            override val description: String = "发布"
            @Composable
            override fun target() {
                val navigator = LocalNavigator.current // 获取当前的navigator实例
                if (navigator != null) {
                    navigator.push(PublishScreen())
                }
            }
        },

        statistics_Screen {
            override val imageVector: ImageVector = Icons.Default.DateRange
            override val description: String = "统计"
            @Composable
            override fun target() {
                StatisticsView()
            }
        },

        Setting_Screen {
            override val imageVector: ImageVector = Icons.Default.Person
            override val description: String = "个人"
            @Composable
            override fun target() {
                SettingView()
            }
        };
        abstract val imageVector: ImageVector
        abstract val description: String
        @Composable
        abstract fun target()
    }


    enum class TabRoute(title: String) {
        Tab1("Tab 1"),
        Tab2("Tab 2"),
        Tab3("Tab 3")
    }
}
package org.example.project.data.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.data.SharedStateManager
import org.example.project.screen.HomeView
import org.example.project.screen.PublishScreen
import org.example.project.screen.ScheduleView
import org.example.project.screen.SettingView
import org.example.project.screen.StatisticsView

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

        Publish_Screen {
            override val imageVector: ImageVector = Icons.Rounded.MoreVert
            override val description: String = "发布"
            @Composable
            override fun target() {
                val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例
                navigator.push(PublishScreen())
                SharedStateManager.currentTab.value = Home_Screen
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


    // 使用枚举构造函数来初始化 title
    enum class TabRoute(val title: String) {
        Find_Screen("发现"),
        Game_Screen("游戏"),
        Music_Screen("音乐")
    }

    enum class PublishNesting() {
        Publish_Image_Screen {
            @Composable
            override fun target() {
                val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例
                //navigator.push(org.example.project.screen.PublishNesting())
                //SharedStateManager.currentTab.value = BarRoute.Publish_Screen
            }
        };

        @Composable
        abstract fun target()
    }
}
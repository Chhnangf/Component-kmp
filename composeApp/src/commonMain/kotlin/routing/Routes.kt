package routing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import screen.HomeView
import screen.ScheduleView
import screen.SettingView
import screen.StatisticsView

enum class Routes {

    Home_Screen {
        override val imageVector: ImageVector = Icons.Default.Home
        override val description: String = "首页"
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
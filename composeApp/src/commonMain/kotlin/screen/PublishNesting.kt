package screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.data.SharedStateManager
import org.example.project.data.navigation.Routes.BarRoute

class PublishNesting : Screen{
    @Composable
    override fun Content() {
        PublishNestingView()
    }

    private @Composable
    fun PublishNestingView() {

        val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例
        SharedStateManager.currentTab.value = BarRoute.Publish_Screen

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "发布新帖") },
                    navigationIcon = {
                        IconButton(onClick = {
                            // 这里应该是导航回到上一屏的逻辑，如果你使用的是 androidx.navigation，则通常是
                            navigator.pop()
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                        }
                    },
                )
            },
            content = {
                Text("PublishNestingView")

            }
        )
    }
}
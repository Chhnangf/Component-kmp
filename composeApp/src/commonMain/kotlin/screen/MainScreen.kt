package screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compoment.data.SharedStateManager
import compoment.navigation.Routes


class MainScreen : Screen {
    private var selectedTabIndex by mutableStateOf(0) // 假设默认选中第一个标签

    @Composable
    override fun Content() {

        Surface {
            Column {
                HeaderBar(selectedTabIndex)

                Box(
                    modifier = Modifier.fillMaxSize().weight(1f)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    SharedStateManager.currentTab.value.target()
                }
                // Main Content
                NavationBar()
            }

        }
    }

    @Composable
    fun NavationBar() {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 4 * Button
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp, 6.dp, 20.dp, 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Routes.BarRoute.entries.forEach {
                    // Button: Icon + Text

                    Column(
                        modifier = Modifier.clickable(
                            onClick = {
                                SharedStateManager.update(it)
                            },
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = it.imageVector,
                            contentDescription = it.description,
                            tint = if (it == SharedStateManager.currentTab.value) androidx.compose.ui.graphics.Color.Blue else androidx.compose.ui.graphics.Color.Black
                        )

                        Text(
                            text = it.description,
                            color = if (it == SharedStateManager.currentTab.value) androidx.compose.ui.graphics.Color.Blue else androidx.compose.ui.graphics.Color.Black
                        )

                    }

                }
            }
        }
    }
}


@Composable
fun HeaderBar(selectedTabIndex: Int, )
{
    val currentTabColor = Color.Blue
    val unselectedTabColor = Color.Black
}



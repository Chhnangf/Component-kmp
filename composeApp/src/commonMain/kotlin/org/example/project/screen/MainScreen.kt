package org.example.project.screen

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.data.SharedStateManager
import org.example.project.data.navigation.Routes


object MainScreen : Screen {

    @Composable
    override fun Content() {

        Surface {
            Column {

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
                            imageVector = if (it == SharedStateManager.currentTab.value) it.selectImage else it.imageVector,
                            contentDescription = it.description,
                            tint = if (it == SharedStateManager.currentTab.value) Color.Blue else Color.Black
                        )

                        Text(
                            text = it.description,
                            color = if (it == SharedStateManager.currentTab.value) Color.Blue else Color.Black
                        )

                    }

                }
            }
        }
    }
}

@Composable
fun PageOneContent() {
    // Your content for Page One here
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
        Text("This is Page One", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun PageTwoContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Text("This is Page Two", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun PageThrContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        Text("This is Page Thr", modifier = Modifier.align(Alignment.Center))
    }
}





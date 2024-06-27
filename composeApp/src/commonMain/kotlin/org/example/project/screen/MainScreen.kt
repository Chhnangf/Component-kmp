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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.example.project.data.PhotoScreenModel
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

                Routes.BarRoute.entries.forEach { route ->
                    // Button: Icon + Text

                    Column(
                        modifier = Modifier.clickable(
                            onClick = { SharedStateManager.update(route) },
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        )
//                            .background(if (it.description == "发布") Color.Red else Color.Transparent)
//                            .clip(RoundedCornerShape(24.dp))
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = if (route == SharedStateManager.currentTab.value) route.selectImage else route.imageVector,
                            contentDescription = route.description,
                            tint = if (route == SharedStateManager.currentTab.value) Color.Blue else Color.Black
                        )

                        Text(
                            text = route.description,
                            fontSize = 11.sp,
                            color = if (route == SharedStateManager.currentTab.value) Color.Blue else Color.Black
                        )

                    }

                }
            }
        }
    }
}



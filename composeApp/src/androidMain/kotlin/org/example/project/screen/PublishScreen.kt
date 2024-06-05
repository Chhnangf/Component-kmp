package org.example.project.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.ktor.http.Url
import io.ktor.util.logging.Logger
import org.example.project.common.ImagePicker
import org.example.project.common.ImageSelector


class PublishScreen : Screen {
    @Composable
    override fun Content() {
        PublishView()
    }
}

@Composable
fun PublishView() {
    val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例
    var showImageSelector by remember { mutableStateOf(false) }

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
        content = {paddingValues ->
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                        }
                    ) {
                        Icon(
                            Icons.Default.Face,
                            contentDescription = "选择图片",
                            modifier = Modifier.size(32.dp).clickable {
                                showImageSelector = true
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("点击选择图片", color = Color.Blue)
                    }
//                    if (imageUri != null) {
//                        AsyncImage(model = imageUri, contentDescription = "预览图片")
//                            .modifier(Height(200.dp))
//                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = "viewModel.postContent.value",
                        onValueChange = {
                            //viewModel.onContentChange(it)
                        },
                        placeholder = { Text("分享你的想法...") },
                        maxLines = Int.MAX_VALUE,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { /* 提交表单的逻辑 */ }),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { /* 发布帖子的逻辑 */ }) {
                        Text(text = "发布")
                    }
                }
            }

        }
    )

    if (showImageSelector) {
        ImageSelector()
    }
}

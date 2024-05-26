package screen

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
import androidx.compose.material.icons.filled.Face

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun PublishView() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "发布新帖") })
        },
        content = {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { }
                    ) {
                        Icon(
                            Icons.Default.Face,
                            contentDescription = "选择图片",
                            modifier = Modifier.size(32.dp)
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
}
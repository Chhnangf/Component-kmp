package org.example.project.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.preat.peekaboo.image.picker.toImageBitmap
import org.example.project.sdk.SpaceXSDK
import org.example.project.data.ImageEntity
import org.koin.core.component.KoinComponent

class PublishScreen() : Screen, KoinComponent {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例
        val imageSDK: SpaceXSDK = getKoin().get()
        val images = remember { mutableStateListOf<ImageEntity>() }
        // 使用 LaunchedEffect 来执行初始化逻辑
        LaunchedEffect(Unit) {
        }



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
            }

        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box {
                    Column(
                        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val sheetState = androidx.compose.material3.rememberModalBottomSheetState()


                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth()
                                .height(100.dp).padding(end = 10.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            // 显示照片
                            ShowImagePicker(images)

                            Spacer(Modifier.width(10.dp))
                            // 打开相机
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Image",
                                tint = Color.White,
                                modifier = Modifier
                                    .clickable {
                                        //showBottomSheet = true
                                        navigator.push(PublishImageScreen())
                                        //ImagePicker.launch()
                                    }
                                    .size(80.dp).aspectRatio(1f).clip(RoundedCornerShape(6.dp))
                                    .background(Color.LightGray).alpha(0.1f),
                            )
                        }

                        CreationField()

                    }


                }
            }
        }
    }
}



@Composable
fun ImageItem(bitmap: ByteArray) {
    Box(
        Modifier
            .size(80.dp) // 设定每个图片格子的大小为正方形
            .aspectRatio(1f) // 保持图片为正方形
            .clip(RoundedCornerShape(6.dp)) // 可选，为图片添加圆角
            .background(Color.LightGray) // 可选，为图片背景着色
    ) {
        Image(
            bitmap = bitmap.toImageBitmap(),
            contentDescription = "Selected Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // 或者 ContentScale.Fit 如果不希望裁剪图片
        )
    }
}

@Composable
fun ShowImagePicker(byteArrayList: List<ImageEntity?>) {
        byteArrayList.forEach { imageEntity ->
            imageEntity?.let { imageEntity ->
                // Convert ByteArray to ImageBitmap for display
                ImageItem(imageEntity.data)
            }
        }
}

/**
 * 创建一个包含正文输入框、添加话题按钮及发布按钮的UI组件。
 * 用户可以在正文输入框中输入内容，点击添加话题按钮可在当前光标位置插入话题标签，
 * 并通过发布按钮提交输入的内容。
 */
@Composable
fun CreationField() {
    // 创建一个可变状态变量来存储标题文本
    val title = remember { mutableStateOf(TextFieldValue("")) }
    val descript = remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val topicName = "#话题" // 这里定义话题名称

    Column(modifier = Modifier) {
        TextField(
            value = title.value,
            onValueChange = { newValue -> title.value = newValue }, // 更新状态变量
            label = { Text("请输入标题") }, // 提示文本
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
        )
    }


    Column (modifier = Modifier.height(200.dp).verticalScroll(rememberScrollState())){
        TextField(
            value = descript.value,
            onValueChange = { newValue -> descript.value = newValue }, // 更新状态变量
            label = { Text("正文") }, // 提示文本
            modifier = Modifier.fillMaxWidth().height(200.dp),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
        )
        Row(verticalAlignment = Alignment.Bottom) {
            TextButton(
                onClick = {
                    // 在文本末尾添加话题标签
                    val currentText = descript.value.text
                    descript.value = descript.value.copy(text = "$currentText $topicName")
                }
            ) {
                Text(topicName)
            }
        }
    }

    Row {
        Button(
            modifier = Modifier.width(150.dp).clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(Color.Red),
            onClick = {},
        ) {
            Text("发布", color = Color.White)
        }
    }
}



package screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.preat.peekaboo.image.picker.ImagePickerLauncher
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import com.preat.peekaboo.ui.camera.PeekabooCamera
import com.preat.peekaboo.ui.camera.rememberPeekabooCameraState


class PublishScreen : Screen {
    @Composable
    override fun Content() {
        PublishView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublishView() {
    val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例
    var showImageSelector by remember { mutableStateOf(false) }
    var selectedImageByteArray by remember { mutableStateOf<List<ByteArray>>(emptyList()) }

    val scope = rememberCoroutineScope()
    val ImagePicker = rememberImagePickerLauncher(
        selectionMode = SelectionMode.Multiple(5),
        scope = scope,
        onResult = { byteArrays ->
            // 直接将所有选中的图片 ByteArray 赋值给状态变量，无需使用 firstOrNull?.let 或 forEach 循环
            selectedImageByteArray = byteArrays.toList()

            // 可以在这里添加处理选中图片的逻辑，比如打印出来验证
            byteArrays.forEach { byteArray ->
                println(byteArray.contentToString())
            }
        }
    )

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
            Surface(modifier = Modifier.fillMaxSize()) {
                Box {
                    Column(
                        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
                        var showBottomSheet by remember { mutableStateOf(false) }

                        Row(modifier = Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth().height(100.dp).padding(end = 10.dp), horizontalArrangement = Arrangement.Start) {

                            ShowImagePicker(selectedImageByteArray)

                            Spacer(Modifier.width(10.dp))
                            // 打开相机
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Image",
                                tint = Color.White,
                                modifier = Modifier
                                    .clickable {
                                        //showBottomSheet = true
                                        //ImagePicker.launch()
                                        navigator.push(PublishNesting())
                                    }
                                    .size(80.dp).aspectRatio(1f).clip(RoundedCornerShape(6.dp)).background(Color.LightGray).alpha(0.1f),
                            )
                        }

                       CreationField()

                        //
                        if (showBottomSheet) {
                            CustomCameraView(ImagePicker)
//                            ModalBottomSheet(
//                                onDismissRequest = {
//                                    showBottomSheet = false
//                                },
//                                sheetState = sheetState
//                            ) {
//                                Box {
//                                    // Sheet content
//
//                                }
//                            }
                        }

                    }

                }
            }
        }

    )
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
fun ShowImagePicker(byteArrayList: List<ByteArray?>) {
        byteArrayList.forEach { byteArray ->
            byteArray?.let { byteArray ->
                // Convert ByteArray to ImageBitmap for display
                ImageItem(byteArray)
            }
        }
}

@Composable
fun SingleImagePicker() {
    val scope = rememberCoroutineScope()
    var selectedImageByteArray by remember { mutableStateOf<ByteArray?>(null) }

    val singleImagePicker = rememberImagePickerLauncher(
        selectionMode = SelectionMode.Single,
        scope = scope,
        onResult = { byteArrays ->
            byteArrays.firstOrNull()?.let {
                // Process the selected images' ByteArrays.
                selectedImageByteArray = it
            }
        }
    )
    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            selectedImageByteArray?.let { byteArray ->
                // Convert ByteArray to ImageBitmap for display
                val imageBitmap = byteArray.toImageBitmap()
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Selected Image",
                    modifier = Modifier.fillMaxSize(), // Adjust modifiers as needed
                )
            }
//         图片选择按钮
            Button(onClick = { singleImagePicker.launch() }) {
                Text("Select Image")
            }
        }

    }
}

@Composable
fun CustomCameraView(ImagePicker: ImagePickerLauncher) {
    val state = rememberPeekabooCameraState(onCapture = { /* Handle captured images */ })
    var showPickImage by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxSize(),) {
        Column {
            PeekabooCamera(
                state = state,
                modifier = Modifier.fillMaxSize(),
                permissionDeniedContent = {
                    // Custom UI content for permission denied scenario
                },
            )
            // Draw here UI you need with provided state
        }

        ////////////////////////////////////////////////////////////////////////////////////////////

        // New Row containing the buttons, floating above the Column
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align the row at the bottom center of the Box
                .padding(bottom = 16.dp), // Optional padding from the bottom edge
            horizontalArrangement = Arrangement.SpaceEvenly // Space buttons evenly
        ) {
            Button(onClick = {
                /* Handle display image action */
                showPickImage = true
            }) {
                Text("图片")
            }
            Button(onClick = {
                /* Call your capture function here */
                state.capture()
            }) {
                Text("拍照")
            }
            Button(onClick = {
                /* Call toggle camera function here */
                state.toggleCamera()
            }) {
                Text("反转")
            }
        }
        if (showPickImage) {
           return ImagePicker.launch()
            //SingleImagePicker()
        }


    }


}

@Composable
fun multipleImagePicker() {
    val scope = rememberCoroutineScope()
    val multipleImagePicker = rememberImagePickerLauncher(
        // Optional: Set a maximum selection limit, e.g., SelectionMode.Multiple(maxSelection = 5).
        // Default: No limit, depends on system's maximum capacity.
        selectionMode = SelectionMode.Multiple(maxSelection = 5),
        scope = scope,
        onResult = { byteArrays ->
            byteArrays.forEach {
                // Process the selected images' ByteArrays.
                println(it)
            }
        }
    )
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



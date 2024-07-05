package org.example.project.component.fileKit

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import io.github.vinceglb.filekit.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.compose.rememberFileSaverLauncher
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import io.github.vinceglb.filekit.core.PlatformDirectory
import io.github.vinceglb.filekit.core.PlatformFile
import io.github.vinceglb.filekit.core.baseName
import io.github.vinceglb.filekit.core.extension
import kotlinx.coroutines.launch
import org.example.project.data.PhotoScreenModel
import org.example.project.data.file.FileData

@Composable
fun SampleApp(screenModel:PhotoScreenModel) {
    var files by remember { mutableStateOf(emptyList<PlatformFile>()) }
    var file by remember { mutableStateOf<ByteArray>(byteArrayOf()) }
    var directory: PlatformDirectory? by remember { mutableStateOf(null) }
    var showDialog by remember { mutableStateOf(false) }
    var currentPhotoIndex = remember { mutableStateOf(0) }


    val singleFilePicker = rememberFilePickerLauncher(type = PickerType.Image,
        title = "Single file picker",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } })

    val singleMediaPicker = rememberFilePickerLauncher(type = PickerType.Video,
        title = "Single file picker",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } })

    val multipleFilesPicker = rememberFilePickerLauncher(type = PickerType.Image,
        mode = PickerMode.Multiple,
        title = "Multiple files picker",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } })

    val filePicker = rememberFilePickerLauncher(type = PickerType.File(listOf("png")),
        title = "Single file picker, only png",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } })

    val filesPicker = rememberFilePickerLauncher(type = PickerType.File(listOf("png")),
        mode = PickerMode.Multiple,
        title = "Multiple files picker, only png",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } })

    val directoryPicker = rememberDirectoryPickerLauncher(title = "Directory picker",
        initialDirectory = directory?.path,
        onResult = { dir -> directory = dir })

    val saver = rememberFileSaverLauncher { file ->
        file?.let { files += it }
    }

    val scope = rememberCoroutineScope()
    fun saveFile(file: PlatformFile) {
        scope.launch {
            saver.launch(
                bytes = file.readBytes(),
                baseName = file.baseName,
                extension = file.extension,
                initialDirectory = directory?.path
            )
        }
    }
    Box {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .background(Color.LightGray)
        ) {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // 静态项：触发图片选择器的按
                items(files.size) { index ->
                    PhotoItem(file = files[index], onClick = {
                        showDialog = true
                        currentPhotoIndex.value = index
                    }, onSaveFile = { file ->
                        saveFile(file)
                    })
                }
            }


            Column(
                Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                // 单文件选择器
//                PickerButton("Single image picker", onClick = { singleFilePicker.launch() })
//
//                // 单视频选择器
//                PickerButton("Single video picker", onClick = { singleMediaPicker.launch() })

                // 多文件选择器
                PickerButton("Multiple image picker", onClick = { multipleFilesPicker.launch() })

//                // 单文件选择器，仅限 PNG
//                PickerButton("Single file picker, only png", onClick = { filePicker.launch() })
//
//                // 多文件选择器，仅限 PNG
//                PickerButton("Multiple files picker, only png", onClick = { filesPicker.launch() })
//
//                // 目录选择器
//                PickerButton(
//                    "Directory picker",
//                    onClick = { directoryPicker.launch() },
//                    enabled = FileKit.isDirectoryPickerSupported()
//                )
            }
            // 使用 mutableStateOf 创建可变状态
            var title by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            // 使用 Column 来垂直排列文本输入框
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Top) {
                // 第一个文本输入框用于标题
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier
                        .fillMaxWidth() // 让文本框宽度充满可用空间
                        .padding(vertical = 8.dp) // 垂直方向上添加一些间距
                )

                // 第二个文本输入框用于多行描述，设置 maxLines 为 Int.MAX_VALUE 允许多行输入
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp).heightIn(min = 48.dp, max = 200.dp),
                    maxLines = 100
                )
            }
            LaunchedEffect(files) {
//            file = files.map {platformFile ->
//                platformFile.readBytes()
//            }
                if (files.isNotEmpty()) {
                    file = files.last().readBytes()
                }

            }

            val coroutineScope = rememberCoroutineScope()

            Button(onClick = {
                val fileObject = FileData(file, title, description)
                /** client post to server*/
                println("UI -> click Button addFileImage -> $fileObject")
                coroutineScope.launch {
                    screenModel.byteArrayState.value = screenModel.addFile(fileObject)
                }

            }) {

                    Text(text = "提交")

            }

            screenModel.byteArrayState.value?.let { byteArray ->
                Text(text = "Data: $byteArray bytes")
            }

            screenModel.byteArrayState.value?.let { byteArray ->
                AsyncImage(
                    byteArray,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }



    }





    if (FileKit.isDirectoryPickerSupported()) {
        Text("Selected directory: ${directory?.path ?: "None"}")
    } else {
        Text("Directory picker is not supported")
    }


}

@Composable
fun PhotoListShade(
    photoItems: List<PlatformFile>,
    currentPhotoIndex: MutableState<Int>,
    onDismissRequest: () -> Unit,
    onSaveFile: (PlatformFile) -> Unit
) {


    val currentFile = photoItems.getOrNull(currentPhotoIndex.value)
    var visible by remember { mutableStateOf(true) }

    if (currentFile != null) { // 确保索引有效
        var bytes by remember(currentFile) { mutableStateOf<ByteArray?>(null) }
        LaunchedEffect(currentFile) {
            bytes = currentFile.readBytes()

        }


        Dialog(onDismissRequest = {
            visible = false
            onDismissRequest()
        }) {

            bytes?.let {
                AsyncImage(
                    bytes,
                    contentDescription = currentFile.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()

                )
            }
        }
    }


}


@Composable
fun DraggableDialog(
    photoItems: List<PlatformFile>, currentPhotoIndex: MutableState<Int>, onDismiss: () -> Unit
) {
    var isDialogVisible by remember { mutableStateOf(true) }

    // Modifier.offset 标记变量
    var backgroundAlpha by remember { mutableStateOf(1f) } // Alpha 初始值为完全不透明
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var totalDragDistanceX by remember { mutableStateOf(0f) } // 用于记录Y轴总拖动距离
    var totalDragDistanceY by remember { mutableStateOf(0f) } // 用于记录Y轴总拖动距离
    val closeThreshold = 450f // 关闭预览的需求偏移量
    val cutoverThreshold = 200f
    val coroutineScope = rememberCoroutineScope()
    val animagtedOffset = animateOffsetAsState(
        Offset(offsetX,offsetY), spring(Spring.DampingRatioLowBouncy,Spring.StiffnessLow)
    )

    val currentFile = photoItems.getOrNull(currentPhotoIndex.value)


    if (currentFile != null) { // 确保索引有效
        var bytes by remember(currentFile) { mutableStateOf<ByteArray?>(null) }
        LaunchedEffect(currentFile) {
            bytes = currentFile.readBytes()

        }

        if (isDialogVisible) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = backgroundAlpha)) // 使用 Alpha 调整背景色
                    .fillMaxSize().wrapContentHeight()
                    .clickable {
                        if (offsetX == 0f && offsetY == 0f) {
                            isDialogVisible = false
                            onDismiss()
                        }
                    }
                    .offset {
                        // 根据拖动的偏移量来调整 Dialog 的位置
                        //IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
                        animagtedOffset.value.round()
                    }.pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                // 重置 totalDragDistance
                                totalDragDistanceY = 0f
                            },
                            onDragEnd = {
                                // 拖动结束时的处理逻辑
                                if (totalDragDistanceY > closeThreshold) {
                                    coroutineScope.launch {
                                        isDialogVisible = false
                                        onDismiss()
                                    }
                                }
                                if (currentPhotoIndex.value < photoItems.lastIndex) {
                                    if (totalDragDistanceX >= cutoverThreshold) {
                                        // 切换到下一张图片
                                        currentPhotoIndex.value = currentPhotoIndex.value + 1
                                    }
                                }
                                if (currentPhotoIndex.value > 0) {
//                                    // 切换到上一张图片
                                    if (totalDragDistanceX <= -cutoverThreshold) {
                                        currentPhotoIndex.value = currentPhotoIndex.value - 1
                                    }
                                }

                                // 重置数据
                                offsetX = 0f
                                offsetY = 0f
                                totalDragDistanceX = 0f
                            }) { change, dragAmount ->
                            // 更新偏移量
                            offsetX += change.position.x - change.previousPosition.x
                            offsetY += change.position.y - change.previousPosition.y

                            // 更新 Alpha 值，例如根据拖动的距离来调整透明度
                            // 这里只是一个示例，你可能需要根据你的具体需求调整 Alpha 的计算方式
                            val alphaChange = (change.previousPosition.y - change.position.y) / 10f
                            backgroundAlpha += alphaChange.coerceIn(-0.01f, 0.01f)
                            backgroundAlpha =
                                backgroundAlpha.coerceIn(0f, 1f) // 确保 Alpha 在 0 到 1 之间

                            // 更新 totalDragDistance，只累加拖动距离的绝对值
                            totalDragDistanceX += -dragAmount.x
                            totalDragDistanceY += kotlin.math.abs(dragAmount.y)
                            println("totalDragDistanceX -> $totalDragDistanceX")

                        }
                    }) {
                // Dialog 的内容
                Column {
                    // 添加 Dialog 的标题、按钮等
                    // ...
                    bytes?.let {
                        AsyncImage(
                            bytes,
                            contentDescription = currentFile.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f).aspectRatio(1f)

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PickerButton(
    title: String, enabled: Boolean = true, onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick, enabled = enabled
        ) {
            Text(title)
        }
    }
}



package org.example.project.screen.component.fileKit

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
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

@Composable
fun SampleApp() {
    var files by remember { mutableStateOf(emptyList<PlatformFile>()) }
    var directory: PlatformDirectory? by remember { mutableStateOf(null) }
    var showDialog by remember { mutableStateOf(false) }
    var currentPhotoIndex = remember { mutableStateOf(0) }


    val singleFilePicker = rememberFilePickerLauncher(
        type = PickerType.Image,
        title = "Single file picker",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } }
    )

    val singleMediaPicker = rememberFilePickerLauncher(
        type = PickerType.Video,
        title = "Single file picker",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } }
    )

    val multipleFilesPicker = rememberFilePickerLauncher(
        type = PickerType.Image,
        mode = PickerMode.Multiple,
        title = "Multiple files picker",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } }
    )

    val filePicker = rememberFilePickerLauncher(
        type = PickerType.File(listOf("png")),
        title = "Single file picker, only png",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } }
    )

    val filesPicker = rememberFilePickerLauncher(
        type = PickerType.File(listOf("png")),
        mode = PickerMode.Multiple,
        title = "Multiple files picker, only png",
        initialDirectory = directory?.path,
        onResult = { file -> file?.let { files += it } }
    )

    val directoryPicker = rememberDirectoryPickerLauncher(
        title = "Directory picker",
        initialDirectory = directory?.path,
        onResult = { dir -> directory = dir }
    )

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

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // 静态项：触发图片选择器的按
            items(files.size) {index ->
                PhotoItem(
                    file = files[index],
                    onClick = {
                        showDialog = true
                        currentPhotoIndex.value = index
                    },
                    onSaveFile = { file ->
                        saveFile(file)
                    }
                )
            }
        }


        Column(
            Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 单文件选择器
            PickerButton("Single image picker", onClick = { singleFilePicker.launch() })

            // 单视频选择器
            PickerButton("Single video picker", onClick = { singleMediaPicker.launch() })

            // 多文件选择器
            PickerButton("Multiple image picker", onClick = { multipleFilesPicker.launch() })

            // 单文件选择器，仅限 PNG
            PickerButton("Single file picker, only png", onClick = { filePicker.launch() })

            // 多文件选择器，仅限 PNG
            PickerButton("Multiple files picker, only png", onClick = { filesPicker.launch() })

            // 目录选择器
            PickerButton(
                "Directory picker",
                onClick = { directoryPicker.launch() },
                enabled = FileKit.isDirectoryPickerSupported()
            )
        }

    }




    if (FileKit.isDirectoryPickerSupported()) {
        Text("Selected directory: ${directory?.path ?: "None"}")
    } else {
        Text("Directory picker is not supported")
    }


    if (showDialog) {
        PhotoListDialog(
            photoItems = files,
            currentPhotoIndex = currentPhotoIndex,
            onDismissRequest = { showDialog = false },
            onSaveFile = ::saveFile
        )
    }
}

@Composable
fun PhotoListDialog(
    photoItems: List<PlatformFile>,
    currentPhotoIndex: MutableState<Int>,
    onDismissRequest: () -> Unit,
    onSaveFile: (PlatformFile) -> Unit
) {

    val currentFile = photoItems.getOrNull(currentPhotoIndex.value)
    if (currentFile != null) { // 确保索引有效
        var bytes by remember(currentFile) { mutableStateOf<ByteArray?>(null) }
        LaunchedEffect(currentFile) {
            bytes = currentFile.readBytes()
        }

        if (photoItems.isNotEmpty()) {
            Dialog(
                onDismissRequest = onDismissRequest
            ) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(horizontal = 16.dp)
                ) {
                    // 显示当前选中的图片
                    AsyncImage(
                        bytes,
                        contentDescription = "Image preview",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f) // 假设图片宽高比为 1:1
                    )

                    // 添加左右切换按钮
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        IconButton(
                            onClick = {
                                if (currentPhotoIndex.value > 0) {
                                    // 切换到上一张图片
                                    currentPhotoIndex.value = currentPhotoIndex.value - 1
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Previous photo"
                            )
                        }

                        Text(
                            text = "${currentPhotoIndex.value + 1} / ${photoItems.size}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                        )

                        IconButton(
                            onClick = {
                                if (currentPhotoIndex.value < photoItems.lastIndex) {
                                    // 切换到下一张图片
                                    currentPhotoIndex.value = currentPhotoIndex.value + 1
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Next photo"
                            )
                        }
                    }

                    Button(
                        onClick = { onSaveFile(photoItems[currentPhotoIndex.value]) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Text("Save Photo")
                    }
                }
            }
        }
    }
}

@Composable
fun PickerButton(
    title: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            enabled = enabled
        ) {
            Text(title)
        }
    }
}

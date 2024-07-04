package org.example.project.screen.component.fileKit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset.Companion.Infinite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import io.github.vinceglb.filekit.core.PlatformFile

@Composable
fun PhotoItem(
    file: PlatformFile,
    onClick: () -> Unit,  // 添加一个点击回调
    onSaveFile: (PlatformFile) -> Unit,
) {
    var bytes by remember(file) { mutableStateOf<ByteArray?>(null) }
    var showName by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(file) {
        bytes = file.readBytes()
    }

    Surface(
        onClick = {
//            showName = !showName
//            showDialog = !showDialog
            onClick()
        },
        modifier = Modifier
            .size(100.dp) // 设置固定尺寸为 50x50
            .aspectRatio(1f)
            .clip(shape = MaterialTheme.shapes.medium)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            bytes?.let {
                AsyncImage(
                    bytes,
                    contentDescription = file.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()

                )
            }

            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = CircleShape,
                modifier = Modifier.align(Alignment.TopEnd).padding(4.dp)
            ) {
                IconButton(
                    onClick = { onSaveFile(file) },
                    modifier = Modifier.size(16.dp),
                ) {
                    Icon(
                        Icons.Default.Check,
                        modifier = Modifier.size(12.dp),
                        contentDescription = "Save",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            AnimatedVisibility(
                visible = showName,
                modifier = Modifier.padding(4.dp).align(Alignment.BottomStart)
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                    shape = MaterialTheme.shapes.small,
                ) {
                    Text(
                        file.name,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = {showDialog = false},
        ) {
            Column {
                // 放大后的图片
                Box(
                    modifier = Modifier
                        .width(300.dp) // 根据需要设置大图的宽度
                        .aspectRatio(1f) // 保持宽高比
                ) {
                    bytes?.let {
                        AsyncImage(
                            bytes,
                            contentDescription = file.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // 保存按钮
                Button(
                    onClick = { onSaveFile(file) },
                    modifier = Modifier
                        .align(CenterHorizontally) // 水平居中
                        .padding(16.dp) // 按钮周围的间距
                ) {
                    Text("保存图片")
                }
            }

        }
    }
}


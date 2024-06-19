package org.example.project.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.AllIcons
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.AlertTriangle
import compose.icons.evaicons.outline.Bulb
import compose.icons.evaicons.outline.Headphones
import compose.icons.evaicons.outline.Info
import compose.icons.evaicons.outline.Monitor
import compose.icons.evaicons.outline.Radio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.cache.CreateImageLoader
import org.example.project.cache.CreateImagePicker
import org.example.project.cache.ImageLoader
import org.example.project.cache.ImagePickerImpl
import org.example.project.data.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScheduleView() {
    val currentTab = remember { mutableStateOf(Routes.TabRoute.Game_Screen) } // 默认显示第二个Tab
    androidx.compose.material.Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { androidx.compose.material.Text(currentTab.value.title) },
                navigationIcon = {
                    // 如果需要，可以在这里添加返回按钮等
                },
                actions = {
                    // 可以在这里添加更多操作按钮
                }
            )
        },
        content = {
            TabContent(currentTab.value)
        }
    )
    // 直接在 TabRow 中使用枚举值
    androidx.compose.material.TabRow(selectedTabIndex = currentTab.value.ordinal) {
        Routes.TabRoute.entries.forEach { tab ->
            androidx.compose.material.Tab(
                text = { androidx.compose.material.Text(tab.title) },
                selected = currentTab.value == tab,
                onClick = { currentTab.value = tab }
            )
        }
    }
}

@Composable
fun TabContent(tab: Routes.TabRoute) {

    // 创建 ImagePicker 和 ImageLoader对象
    val imagePickerImpl = CreateImagePicker()
    val imageLoader = CreateImageLoader()

    var images by remember { mutableStateOf<List<String>>(emptyList()) }
    LaunchedEffect(key1 = Unit) {
        images = withContext(Dispatchers.IO) {
            imagePickerImpl.fetchImages()
        }
    }

    LazyColumn {
        items(images) { imagePath ->
            val imageBitmap by rememberImagePainter(imagePath, imageLoader)
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun rememberImagePainter(imagePath: String, imageLoader: ImageLoader): State<ImageBitmap?> {
    // 使用 remember 和 LaunchedEffect 来加载和更新图片
    return remember(imagePath) {
        mutableStateOf<ImageBitmap?>(null)
    }.apply {
        LaunchedEffect(imagePath) {
            try {
                val bitmap = withContext(Dispatchers.IO) {
                    imageLoader.load(imagePath)
                }
                value = bitmap
            } catch (e: Exception) {
                // 处理异常，例如设置一个错误图片或记录日志
            }
        }
    }
}


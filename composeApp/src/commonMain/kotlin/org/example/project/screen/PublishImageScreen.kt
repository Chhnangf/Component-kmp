package org.example.project.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.preat.peekaboo.image.picker.ImagePickerLauncher
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.ui.camera.PeekabooCamera
import com.preat.peekaboo.ui.camera.rememberPeekabooCameraState
import org.example.project.viewmodel.PublishViewModel

data class PublishImageScreen(val viewModel: PublishViewModel) : Screen{
    @Composable
    override fun Content() {
        //PublishImageView(viewModel)
        val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例

        val scope = rememberCoroutineScope()

//        fun onImageCaptured(byteArray: ByteArray) {
//            viewModel.addImageByteArray(listOf(byteArray))
//            println("Image captured. Size: ${byteArray.size} bytes")
//        }

        val ImagePicker = rememberImagePickerLauncher(
            selectionMode = SelectionMode.Multiple(5),
            scope = scope,
            onResult = { byteArrays ->
                // 直接将所有选中的图片 ByteArray 赋值给状态变量，无需使用 firstOrNull?.let 或 forEach 循环
                // selectedImageByteArray = byteArrays.toList().toMutableList()
                viewModel.addImageByteArray(byteArrays)
                // 可以在这里添加处理选中图片的逻辑，比如打印出来验证
                byteArrays.forEach { byteArray ->
                    println(byteArray.contentToString())
                }
            }
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
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
                //CustomCameraView(::onImageCaptured,ImagePicker)

                val navigator = LocalNavigator.currentOrThrow // 获取当前的navigator实例

                val state = rememberPeekabooCameraState(onCapture = {
                    /* Handle captured images */
                    // 当图片被捕获时，调用外部传入的回调
                    //onCapture(it ?: return@rememberPeekabooCameraState)
                    //println("onCapture: ${onCapture}")

                    navigator.pop()
                })
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
                            ImagePicker.launch()
                            println("点击图片: ")
                        }) {
                            Text("图片")
                        }
                        Button(onClick = {
                            /* Call your capture function here */
                            state.capture()
                            navigator.pop()
                            println("点击拍照: ")
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
                        //SingleImagePicker()
                    }
                }
            }
        )
    }

}
@Composable
fun PublishImageView(viewModel: PublishViewModel) {


}
@Composable
fun CustomCameraView(onCapture: (ByteArray) -> Unit,ImagePicker: ImagePickerLauncher) {


}

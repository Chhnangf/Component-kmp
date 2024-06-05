package screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import com.preat.peekaboo.ui.camera.PeekabooCamera
import com.preat.peekaboo.ui.camera.rememberPeekabooCameraState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleView() {
    Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        Button(onClick = {showBottomSheet = true}) {
            Text("拍照")
        }
        singleImagePicker()

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // Sheet content
                CustomCameraView()
            }
        }
    }

}

@Composable
fun singleImagePicker() {
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

    Column (modifier = Modifier.fillMaxSize()) {
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

    Button(
        onClick = {
            multipleImagePicker.launch()
        }
    ) {
        Text("Pick Multiple Images")
    }
}

@Composable
fun CustomCameraView() {
    val state = rememberPeekabooCameraState(onCapture = { /* Handle captured images */ })
    Box(modifier = Modifier.fillMaxSize()) {
        PeekabooCamera(
            state = state,
            modifier = Modifier.fillMaxSize(),
            permissionDeniedContent = {
                // Custom UI content for permission denied scenario
            },
        )
        // Draw here UI you need with provided state

    }
}
package org.example.project.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual object ImagePicker {
    @Composable
    actual fun pickImage() {
        ImageSelector()
    }

}

@Composable
fun ImageSelector() {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
            }
        }
    )
    galleryLauncher.launch("image/*")
//    Column {
//        imageUri?.let {
//            Image(
//                painter = rememberAsyncImagePainter(model = imageUri),
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(36.dp)
//            )
//        }
//
//        TextButton(
//            onClick = {
//
//            }
//        ) {
//            Text(
//                text = "Pick image"
//            )
//        }
//    }
}
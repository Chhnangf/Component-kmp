package org.example.project.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.example.project.common.OpenGallery
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class OpenGallery(private val activity: ComponentActivity) {

    // 移除直接在类级别初始化的getImageLauncher
    actual suspend fun pickImage(): String? {
        TODO("Not yet implemented")
    }

}

package org.example.project.cache

import ImagePicker
import androidx.compose.ui.graphics.ImageBitmap


actual class ImagePickerImpl: ImagePicker {
    actual override suspend fun fetchImages(): List<String> {
        return emptyList()
    }
}

actual fun CreateImagePicker(): ImagePickerImpl {
    return ImagePickerImpl()
}

actual class ImageLoader() {
    actual suspend fun load(imagePath: String): ImageBitmap {

        return TODO()
    }
}

actual fun CreateImageLoader(): ImageLoader {
    return ImageLoader()
}
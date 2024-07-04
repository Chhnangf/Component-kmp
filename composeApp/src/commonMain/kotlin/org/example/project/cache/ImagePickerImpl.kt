package org.example.project.cache

import ImagePicker
import androidx.compose.ui.graphics.ImageBitmap

expect class ImagePickerImpl: ImagePicker {
    override suspend fun fetchImages(): List<String>
}

expect fun CreateImagePicker(): ImagePickerImpl

expect class ImageLoader {
    suspend fun load(imagePath: String): ImageBitmap

}
expect fun CreateImageLoader(): ImageLoader

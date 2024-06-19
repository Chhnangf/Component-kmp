package org.example.project.cache

import ImagePicker
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap

actual class ImagePickerImpl(private val contentResolver: ContentResolver) : ImagePicker {

    actual override suspend fun fetchImages(): List<String> {
        return withContext(Dispatchers.IO) {
            fetchImagePaths()
        }
    }

    private fun fetchImagePaths(): List<String> {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val imagePaths = mutableListOf<String>()
        val dataColumnIndex = projection.indexOf(MediaStore.Images.Media.DATA)

        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val imagePath = cursor.getString(dataColumnIndex)
                imagePaths.add(imagePath)
            }
        }

        return imagePaths
    }
}

actual fun CreateImagePicker(): ImagePickerImpl {
    val appContext: Context = AndroidContents.localContext!!
    val contentResolver: ContentResolver = appContext.contentResolver
    return ImagePickerImpl(contentResolver)
}

actual class ImageLoader () {
    actual suspend fun load(imagePath: String): ImageBitmap {
        // 使用 Android 的 API 加载图片
        val file = File(imagePath)
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        return bitmap.asImageBitmap()
    }

    companion object {
        fun create(): ImageLoader = ImageLoader()
    }
}

actual fun CreateImageLoader(): ImageLoader {
    return ImageLoader()
}
package org.example.project.cache

import ImagePicker
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
actual class ImagePickerImpl(private val contentResolver: ContentResolver): ImagePicker {

    actual override suspend fun fetchImages(): List<String> {
        return withContext(Dispatchers.IO) {
            fetchImagePaths(contentResolver)
        }
    }

    private fun fetchImagePaths(contentResolver: ContentResolver): List<String> {

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val imagePaths = mutableListOf<String>()

        var cursor: Cursor? = null
        try {
            cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
            )


            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val dataColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    val imagePath = cursor.getString(dataColumnIndex)
                    imagePaths.add(imagePath)
                } while (cursor.moveToNext())
            }
            imagePaths
        } finally {
            cursor?.close()
        }
        return imagePaths
    }
}
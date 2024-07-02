package org.example.project.media

import android.provider.MediaStore
import androidx.compose.ui.graphics.asAndroidBitmap
import coil3.Bitmap
import com.preat.peekaboo.image.picker.toImageBitmap
import org.example.project.cache.AndroidContents
import java.io.ByteArrayOutputStream
import java.util.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

actual object MediaStore {
    actual fun storePhoto(image: ByteArray, titile: String?, desc: String?) {
        val context = AndroidContents.localContext
        val mResolver = context?.contentResolver
        if (mResolver == null) {
            println("ContentResolver is null, cannot store photo")
            return
        }

        try {
            val bitmap = image.toImageBitmap().asAndroidBitmap()

            MediaStore.Images.Media.insertImage(
                mResolver,
                bitmap,
                titile,
                desc
            )
            println("Photo stored successfully with title: $titile, desc: $desc")
        } catch (e: Exception) {
            println("Failed to store photo $e")
        }
    }
}

@OptIn(ExperimentalEncodingApi::class)
actual fun ByteArray.toDataUrl(): String? {
    val bitmap:android.graphics.Bitmap = this.toImageBitmap().asAndroidBitmap()
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
    val byteArray:ByteArray = byteArrayOutputStream.toByteArray()
    return "data:image/jpeg;base64," + kotlin.io.encoding.Base64.encode(byteArray)
}
package org.example.project.media

actual object MediaStore {
    actual fun storePhoto(image: ByteArray, titile:String?,desc:String?) {
        return TODO()
    }
}

actual fun ByteArray.toDataUrl(): String? {
    return TODO()
}
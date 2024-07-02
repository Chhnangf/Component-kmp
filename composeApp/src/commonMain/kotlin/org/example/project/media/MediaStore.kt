package org.example.project.media

expect object MediaStore {
    fun storePhoto(image: ByteArray, titile:String?,desc:String?)
}

expect fun ByteArray.toDataUrl(): String?
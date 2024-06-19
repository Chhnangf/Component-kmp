package org.example.project.cache

import ImagePicker

expect class ImagePickerImpl: ImagePicker {
    override suspend fun fetchImages(): List<String>
}
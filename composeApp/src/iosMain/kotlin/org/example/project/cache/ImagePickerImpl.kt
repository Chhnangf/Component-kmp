package org.example.project.cache

import ImagePicker

actual class ImagePickerImpl: ImagePicker {
    actual override suspend fun fetchImages(): List<String> {
        return emptyList()
    }
}
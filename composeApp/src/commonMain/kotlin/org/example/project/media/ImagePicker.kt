package org.example.project.media

interface ImagePicker {
    suspend fun fetchImages(): List<String>
}
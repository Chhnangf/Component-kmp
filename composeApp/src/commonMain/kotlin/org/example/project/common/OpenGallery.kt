package org.example.project.common
expect class OpenGallery {
    suspend fun pickImage(): String?
}

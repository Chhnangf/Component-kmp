package org.example.project.data

import kotlinx.coroutines.flow.Flow



interface ImageDatabase {
    suspend fun insertImage(imageData: ByteArray): Long
    fun getAllImages(): Flow<List<ImageEntity>>
    suspend fun getImageById(id: Long): ImageEntity?
    suspend fun deleteImage(id: Long)
}

data class ImageEntity(
    val id: Long,
    val data: ByteArray,
    val createdAt: String
)
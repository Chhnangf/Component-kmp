package org.example.project

import org.example.project.cache.Database
import org.example.project.cache.DatabaseDriverFactory
import org.example.project.data.ImageEntity

class ImageSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)

    fun insertImage(imageData: ByteArray) {
        database.insertImage(imageData)
    }

    fun getAllImages(): List<ImageEntity> {
        return database.getAllImages().map {
            ImageEntity(
                id = it.id,
                data = it.data,
                createdAt = it.createdAt
            )
        }
    }
}
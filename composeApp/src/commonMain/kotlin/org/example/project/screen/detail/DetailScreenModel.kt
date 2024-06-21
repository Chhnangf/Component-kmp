package org.example.project.screen.detail

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.Flow
import org.example.project.data.PhotoObject
import org.example.project.data.PhotoRepository

class DetailScreenModel(private val photoRepository: PhotoRepository): ScreenModel {
    fun getObject(objectID: Int): Flow<PhotoObject?> =
        photoRepository.getObjectByID(objectID)
}
package org.example.project.data

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * PhotoScreenModel 类负责管理和提供照片数据给 UI 层。
 *
 * @param photoRepository PhotoRepository 实例（由构造函数传入），用于与数据源（如 API 或数据库）交互获取照片数据。
 *
 * 此 ScreenModel 设计用于初始化时自动加载照片列表，并通过状态管理让 UI 能响应数据变化。
 */
class PhotoScreenModel(private val photoRepository: PhotoRepository) : ScreenModel {
    /**
     * 获取照片列表的状态流。
     * 这个状态流使用 [stateIn] 来缓存，直到有订阅者，缓存时间为 5 秒。
     * 如果在 5 秒内没有订阅者，缓存的数据将被清除。
     */
    val objects: StateFlow<List<PhotoObject>> =
        photoRepository.getObjects()
            .stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
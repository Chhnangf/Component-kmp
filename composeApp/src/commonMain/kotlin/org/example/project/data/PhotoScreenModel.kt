package org.example.project.data

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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


    /**
     * 向服务器添加新的照片数据。
     * @param photoObjects 要添加的照片对象列表。
     */
     fun addPhotoObjects(photoObjects: PhotoObject) {
        screenModelScope.launch {
            try {
                // 调用 repository 的 addPhoto 方法，并获取结果
                val result = photoRepository.postPhoto(photoObjects)
                println("调用addPhotoObjects$result")
                // 处理结果，例如更新 UI 或显示消息
                if (result != null) {
                    // 添加成功，可能需要更新 UI 或状态
                } else {
                    // 处理添加失败的情况
                }
            } catch (e: Exception) {
                // 捕获并处理异常
                // 例如显示错误信息
            }
        }
    }

    // 下拉刷新，调用Api从ktor_server获取数据从 photoApi 获取最新数据并调用 photoStorage 保存。
    fun refresh() {
        screenModelScope.launch {
            photoRepository.refresh()
        }
    }
}
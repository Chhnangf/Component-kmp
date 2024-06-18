package org.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.data.ImageEntity

class ImageLaunchViewModel (private val sdk: ImageSDK): ViewModel() {
    private val _state = mutableStateOf(ImageLaunchScreenState())
    val state: State<ImageLaunchScreenState> = _state

    init {
        loadImages()
    }
    fun loadImages() {
        viewModelScope.launch {

            val images = sdk.getAllImages()
            _state.value = _state.value.copy(launches = images)
        }
    }
}

data class ImageLaunchScreenState(
    val launches: List<ImageEntity> = emptyList()
)
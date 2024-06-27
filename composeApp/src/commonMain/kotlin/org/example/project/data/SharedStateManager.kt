package org.example.project.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.project.data.navigation.Routes


object SharedStateManager {

    // 使用 MutableStateFlow 替代 mutableStateOf，以便使用 Flow 收集状态变化
    private val _currentTab = MutableStateFlow(Routes.BarRoute.Home_Screen)
    val currentTab: StateFlow<Routes.BarRoute> = _currentTab

    fun update(newValue: Routes.BarRoute) {
        // 只有当新值与当前值不同时，才更新状态
        if (newValue != _currentTab.value) {
            _currentTab.value = newValue
        }
    }


}


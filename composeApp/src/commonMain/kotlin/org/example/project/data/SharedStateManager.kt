package org.example.project.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.example.project.data.navigation.Routes


object SharedStateManager {

    private var _currentTab = mutableStateOf(Routes.BarRoute.Home_Screen)
    val currentTab: MutableState<Routes.BarRoute>
        get() = this._currentTab

    fun update(newValue: Routes.BarRoute) {
        this._currentTab.value = newValue
    }

}


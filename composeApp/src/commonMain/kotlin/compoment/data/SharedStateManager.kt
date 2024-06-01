package compoment.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import compoment.navigation.Routes


object SharedStateManager {

    private var _currentTab = mutableStateOf(Routes.BarRoute.Home_Screen)
    val currentTab: MutableState<Routes.BarRoute>
        get() = this._currentTab

    fun update(newValue: Routes.BarRoute) {
        this._currentTab.value = newValue
    }

}

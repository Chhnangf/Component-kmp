package viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn


object SharedStateManager {
    private val _state: MutableState<Int> = mutableStateOf(0)
    val state: MutableState<Int> get() = _state

    private fun updateState(newValue: Int) {
        this._state.value = newValue
    }

    fun increase() {
       updateState(state.value + 1)
    }
    fun descrease() {
        updateState(state.value - 1)
    }

}


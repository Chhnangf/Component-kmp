package org.example.project.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch
import org.example.project.cache.DatabaseDriverFactory
import org.example.project.entity.RocketLaunch
import org.example.project.network.SpaceXApi
import org.example.project.sdk.SpaceXSDK

/**
 * Composable function that displays the launch history of SpaceX.
 *
 * 该可组合项利用 [SpaceXLaunchViewModel] 来获取和呈现历史数据
 * 关于 SpaceX 发射。 ViewModel 使用 [SpaceXSDK] 初始化，
 * 为本地数据处理提供必要的 [DatabaseDriverFactory] 和
 * 用于获取远程数据的 [SpaceXApi] 实例。
 *
 * @param viewModel 用于提供 [SpaceXLaunchViewModel] 外部实例的可选参数。
 * 如果未提供，将创建一个新实例并注入依赖项。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceXLaunchHistory(
    // 通过Lambda表达式在viewModel作用域内实例化数据库和ViewModel
    viewModel: SpaceXLaunchViewModel = viewModel {
        SpaceXLaunchViewModel(
            SpaceXSDK(databaseDriverFactory = DatabaseDriverFactory(), api = SpaceXApi())
        )
    }
) {
    /**
     * Utilizes the `remember` function in conjunction with a State Hoister to keep track of the
     * [SpaceXLaunchViewModel]'s state within a Composable. This line of code creates a snapshot state
     * that caches the ViewModel's state, ensuring that the Composable only recomposes when the state
     * changes, leading to efficient UI updates.
     *
     * @param viewModel The ViewModel whose state we're observing. It should provide the current state
     *                  of the SpaceX launch data or any relevant UI-related data.
     *
     * Returns a RocketLaunchScreenState object `state` that holds the current value of the ViewModel's state and is
     * automatically updated whenever the ViewModel's state changes. This is particularly useful for
     * reflecting changes in the UI driven by data updates from the ViewModel.
     */
    val state by remember { viewModel.state }

    // Views...
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        viewModel.loadLaunches()
        pullToRefreshState.endRefresh()
    }

    val app_theme_successful = Color(0xff4BB543)
    val app_theme_unsuccessful = Color(0xffFC100D)

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "SpaceX Launches",
                    style = MaterialTheme.typography.headlineLarge
                )
            })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Loading...", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn {
                    items(state.launches) { launch: RocketLaunch ->
                        Column(modifier = Modifier.padding(all = 16.dp)) {
                            Text(
                                text = "${launch.missionName} - ${launch.launchYear}",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = if (launch.launchSuccess == true) "Successful" else "Unsuccessful",
                                color = if (launch.launchSuccess == true) app_theme_successful else app_theme_unsuccessful
                            )
                            Spacer(Modifier.height(8.dp))
                            val details = launch.details
                            if (details?.isNotBlank() == true) {
                                Text(
                                    text = details
                                )
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }

            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }

}



/**
 * viewModel负责管理和提供SpaceX火箭发射数据给UI层
 *
 * @param sdk SPaceXSDK 实例（由构造函数传入），用于与数据源（如API或数据库）交互获取火箭发射数据。
 * @param id UUID实例，用于标识ViewModel实例。
 *
 * 此ViewModel设计用于初始化时自动加载火箭发射列表，并通过状态管理让ui能响应数据变化
 */
class SpaceXLaunchViewModel(private val sdk: SpaceXSDK, val id: Uuid = uuid4()) : ViewModel() {

    /**
     * 内部持有的状态变量，用于管理火箭发射列表的加载状态和数据。
     * 使用mutableStateOf确保与Compose UI的双向绑定，当值变化时自动更新UI。
     */
    private val _state = mutableStateOf(RocketLaunchScreenState())

    /**
     * 提供给外部只读访问的状态，UI层通过观察此状态的变化来更新界面。
     */
    val state: MutableState<RocketLaunchScreenState> = _state

    /**
     * 初始化方法。
     * 在ViewModel创建时自动调用，触发数据的初次加载。
     */
    init {
        loadLaunches()
    }

    /**
     * 加载火箭发射列表数据的方法。
     *
     * 异步执行，先将加载状态设为true，然后尝试从数据源加载数据。
     * 成功时更新状态，设置加载状态为false并填充数据列表。
     * 若加载过程中发生异常，则恢复加载状态为false，并清空数据列表，确保UI能反馈错误情况。
     */
    fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, launches = emptyList())
            try {
                val launches = sdk.getLaunches(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, launches = launches)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, launches = emptyList())
            }
        }
    }
}

/**
 * 数据类，用于存储火箭发射界面的状态信息。
 *
 * @property isLoading Boolean，表示数据是否处于加载状态。
 * @property launches List<RocketLaunch>，存储火箭发射项的列表。每个[RocketLaunch]对象代表一次具体的发射信息。
 *
 * 此类用于ViewModel中管理界面显示的全部状态，确保UI能够反映数据加载过程及具体内容。
 */
data class RocketLaunchScreenState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList()
)
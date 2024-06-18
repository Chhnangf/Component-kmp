package org.example.project
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.factory.KoinViewModelFactory


@Composable
fun AndroidApp() {
    val viewModel = koinViewModel<ImageLaunchViewModel>()
    val state by remember {viewModel.state}
    viewModel.loadImages()
}
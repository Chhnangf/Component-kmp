import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.di.initKoin
import org.example.project.screen.HomeScreen
import org.example.project.screen.MainScreen

@Composable
fun App() {
    MaterialTheme {
        initKoin()

        //Navigator(HomeScreen)

        // test
        Navigator(MainScreen)
    }
}
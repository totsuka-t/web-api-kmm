import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch

class WeatherScreen(private val onClick: () -> Unit) : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("WeatherForecastAPI") },
                    navigationIcon = {
                        IconButton(onClick = onClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    },
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val scope = rememberCoroutineScope()
                Text("WeatherScreen")
                Button({
                    scope.launch {
                        WeatherAPIClient().fetchWeather(52.52, 13.41, "Asia%2FTokyo", 1)
                    }
                }) {

                }
            }
        }
    }
}
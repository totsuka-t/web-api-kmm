
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch

class WeatherScreen(private val onClick: () -> Unit) : Screen {

    @Composable
    override fun Content() {
        var weather by remember { mutableStateOf<Weather?>(null) }

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
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                weather?.let {
                    for (i in it.hourly.time.indices) {
                        Row {
                            Column {
                                Text("time: ${it.hourly.time[i]}")
                                Text("temperature: ${it.hourly.temperature2m[i]}")
                                Text("precipitationProbability: ${it.hourly.precipitationProbability[i]}")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            when (it.hourly.precipitationProbability[i]) {
                                in 0..30 -> {
                                    Icon(
                                        Icons.Filled.WbSunny,
                                        contentDescription = null,
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        tint = Color(0xFFF57C00)
                                    )
                                }
                                in 31..70 -> {
                                    Icon(
                                        Icons.Filled.Cloud,
                                        contentDescription = null,
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        tint = Color(0xFF616161)
                                    )
                                }
                                in 71..100 -> {
                                    Icon(
                                        Icons.Filled.WaterDrop,
                                        contentDescription = null,
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        tint = Color(0xFF1976D2)
                                    )
                                }
                            }

                        }
                        Divider()
                    }
                }

                val scope = rememberCoroutineScope()
                Button({
                    scope.launch {
                        weather = WeatherAPIClient().fetchWeather(52.52, 13.41, "Asia%2FTokyo", 1)
                    }
                }) {
                    Text("fetch weather")
                }
            }
        }
    }
}
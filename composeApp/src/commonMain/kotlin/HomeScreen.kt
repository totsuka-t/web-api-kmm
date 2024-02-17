import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Case Studies") },
                )
            }
        ) {
            Column {
                ListTitle("NumbersAPI") {
                    navigator.push(NumbersScreen {
                        navigator.pop()
                    })
                }
                ListTitle("WeatherForecastAPI") {
                    navigator.push(WeatherScreen {
                        navigator.pop()
                    })
                }
                ListTitle("PokeAPI") {
                    navigator.push(PokemonScreen {
                        navigator.pop()
                    })
                }
            }
        }
    }
}

@Composable
private fun ListTitle(title: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 16.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
            )
        }
    }
}
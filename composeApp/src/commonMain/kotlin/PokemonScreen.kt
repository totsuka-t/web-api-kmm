
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonScreen(private val onClick: () -> Unit) : Screen {

    @Composable
    override fun Content() {
        var pokemon by remember { mutableStateOf<Pokemon?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            pokemon?.let {
                val painterResource = asyncPainterResource(data = it.sprites.frontDefault) {
                    coroutineContext = Job() + Dispatchers.IO
                }
                KamelImage(painterResource, contentDescription = null)
            }

            val scope = rememberCoroutineScope()
            Button({
                scope.launch {
                    pokemon = PokeAPIClient().fetchPokemon(1)
                }
            }) {
                Text("tapped")
            }
        }
    }
}
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch

class PokemonScreen(private val onClick: () -> Unit) : Screen {

    @Composable
    override fun Content() {
        var pokemon by remember { mutableStateOf<Pokemon?>(null) }

        val scope = rememberCoroutineScope()
        Button({
            scope.launch {
                pokemon = PokeAPIClient().fetchPokemon(1)
                println("result: $pokemon")
            }
        }) {
            Text("tapped")
        }
    }
}

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PokemonScreen(private val onClick: () -> Unit) : Screen {

    @Composable
    override fun Content() {
        var pokemon1 by remember { mutableStateOf<Pokemon?>(null) }
        var pokemon2 by remember { mutableStateOf<Pokemon?>(null) }
        var pokemon3 by remember { mutableStateOf<Pokemon?>(null) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("PokeAPI") },
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
                pokemon1?.let {
                    val painterResource = asyncPainterResource(data = it.sprites.frontDefault) {
                        coroutineContext = Job() + Dispatchers.IO
                    }
                    KamelImage(
                        painterResource,
                        contentDescription = null,
                        modifier =  Modifier.size(200.dp)
                    )
                }
                pokemon2?.let {
                    val painterResource = asyncPainterResource(data = it.sprites.frontDefault) {
                        coroutineContext = Job() + Dispatchers.IO
                    }
                    KamelImage(
                        painterResource,
                        contentDescription = null,
                        modifier =  Modifier.size(200.dp)
                    )
                }
                pokemon3?.let {
                    val painterResource = asyncPainterResource(data = it.sprites.frontDefault) {
                        coroutineContext = Job() + Dispatchers.IO
                    }
                    KamelImage(
                        painterResource,
                        contentDescription = null,
                        modifier =  Modifier.size(200.dp)
                    )
                }

                val scope = rememberCoroutineScope()
                Button({
                    scope.launch {
                        val pokemons = listOf(
                            async { PokeAPIClient().fetchPokemon(1) },
                            async { PokeAPIClient().fetchPokemon(2) },
                            async { PokeAPIClient().fetchPokemon(3) }
                        ).awaitAll()

                        pokemons.map {
                            if (it.id == 1) {
                                pokemon1 = it
                            } else if (it.id == 2) {
                                pokemon2 = it
                            } else if (it.id == 3) {
                                pokemon3 = it
                            } else {
                                println("unknown: id = ${it.id}")
                            }
                        }
                    }
                }) {
                    Text("tapped")
                }
            }
        }
    }
}
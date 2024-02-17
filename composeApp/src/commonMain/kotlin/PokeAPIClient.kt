import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class PokeAPIClient {
    private val httpClient = HttpClient()
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun fetchPokemon(id: Int): Pokemon {
        println("fetch pokemon: id = $id")
        val response = httpClient.get("https://pokeapi.co/api/v2/pokemon/$id")
        return json.decodeFromString<Pokemon>(response.body())
    }
}
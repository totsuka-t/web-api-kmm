import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NumbersAPIClient {
    private val httpClient = HttpClient()

    suspend fun fetchTrivia(id: Int): String {
        val response = httpClient.get("http://numbersapi.com/$id/trivia")
        return response.body()
    }
}
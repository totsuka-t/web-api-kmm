import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class WeatherAPIClient {
    private val httpClient = HttpClient()

    suspend fun fetchWeather(lat: Double, lon: Double, timezone: String, forecastDays: Int): Weather {
        println("fetch weather")
        val baseUrl = "https://api.open-meteo.com/v1/forecast"
        val latitude = "latitude=$lat"
        val longitude = "longitude=$lon"
        val hourly = "hourly=temperature_2m,precipitation_probability"
        val timezone = "timezone=$timezone"
        val forecastDays = "forecast_days=$forecastDays"

        val response = httpClient.get("$baseUrl?$latitude&$longitude&$hourly&$timezone&$forecastDays")

        return Json.decodeFromString<Weather>(response.body())
    }
}
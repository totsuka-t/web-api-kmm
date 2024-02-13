import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double,
    val hourly: Hourly,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
) {
    @Serializable
    data class Hourly(
        @SerialName("precipitation_probability")
        val precipitationProbability: List<Int>,
        @SerialName("temperature_2m")
        val temperature2m: List<Double>,
        val time: List<String>
    )

    @Serializable
    data class HourlyUnits(
        @SerialName("precipitation_probability")
        val precipitationProbability: String,
        @SerialName("temperature_2m")
        val temperature2m: String,
        val time: String
    )
}
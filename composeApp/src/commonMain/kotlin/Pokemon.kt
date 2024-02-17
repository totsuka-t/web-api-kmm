import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val height: Int,
    val weight: Int,
) {
    @Serializable
    data class Sprites(
        @SerialName("front_default")
        val frontDefault: String
    )
}
# web-api-kmm
This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

## Feature
### [NumbersAPI](http://numbersapi.com)
An API for interesting facts about numbers.

This application retrieves trivia based on the information entered in the TextField.

### [WeatherForecastAPI](https://open-meteo.com/en/docs)
An API for accessing current weather data, forecasts, and historical data.

This application retrieves the day's weather information based on the latitude and longitude specified in advance.

### [PokéAPI](https://pokeapi.co/)
An API for RESTful web service that provides access to a vast amount of Pokémon-related information.

This application acquires images of Pokémon associated with IDs specified in advance.

## Dependencies
This application depends on the following libraries.

### [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
Serialize the obtained Json data.

### [Kamel](https://github.com/Kamel-Media/Kamel)
Used to display images retrieved from the network.

### [Voyager](https://github.com/adrielcafe/voyager)
voyager is used for screen transitions.

### [Ktor](https://ktor.io/)
Ktor is used for HTTP/HTTPS.

import android.content.Context
import android.os.Build
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.Credentials.basic
import java.net.HttpURLConnection
import java.net.URL

class AndroidPlatform : Platform {

    init {
        println("call method")
        CoroutineScope(Dispatchers.IO).launch {
            // URL 設定
            val url = URL("http://numbersapi.com/5/trivia")
            val con = url.openConnection() as HttpURLConnection

            // 接続設定(ミリ秒で指定)
            con.connectTimeout = 20_000 // 20 秒
            con.readTimeout = 20_000    // 20 秒
            con.requestMethod = "GET"   // GETの場合は省略可能

            // 接続を確立
            con.connect()

            // レスポンスを取得
            val str = con.inputStream.bufferedReader(Charsets.UTF_8).use { br ->
                br.readLines().joinToString("")
            }

            // 表示
            println("result: $str")
        }
    }

    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
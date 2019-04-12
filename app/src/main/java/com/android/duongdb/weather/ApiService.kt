package com.android.duongdb.weather

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import java.net.URLEncoder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


const val BASE_URL = "https://weather-ydn-yql.media.yahoo.com/forecastrss"

interface ApiService {

    @GET("/?location=sunnyvale,ca&format=json")
    fun getWeather(
            @Header("Authorization") header: String,
            @Header("X-Yahoo-App-Id") appID: String = "ZV4O165a",
            @Header("Content-Type") contentType: String = "application/json"
    ): Observable<WeatherRetrofit>


    companion object {

        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        fun createWeatherService(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        @SuppressLint("NewApi")
        fun getAuthorHeaderRequest(): String {
            val consumerKey = "dj0yJmk9amhSZHNLRTBCc3JMJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWYy"
            val consumerSecret = "9a0be2cde8febf35ffc19413cd9d4ef64944cc11"
            val url = "https://weather-ydn-yql.media.yahoo.com/forecastrss"

            val timestamp = Date().time / 1000
            val nonce = ByteArray(32)
            val rand = Random()
            rand.nextBytes(nonce)
            val oauthNonce = String(nonce).replace("\\W".toRegex(), "")

            val parameters = ArrayList<String>()
            parameters.add("oauth_consumer_key=$consumerKey")
            parameters.add("oauth_nonce=$oauthNonce")
            parameters.add("oauth_signature_method=HMAC-SHA1")
            parameters.add("oauth_timestamp=$timestamp")
            parameters.add("oauth_version=1.0")
            // Make sure value is encoded
            parameters.add("location=" + URLEncoder.encode("sunnyvale,ca", "UTF-8"))
            parameters.add("format=json")
            parameters.sort()
            val parametersList = StringBuffer()
            for (i in parameters.indices) {
                parametersList.append((if (i > 0) "&" else "") + parameters.get(i))
            }

            val signatureString = "GET&" +
                    URLEncoder.encode(url, "UTF-8") + "&" +
                    URLEncoder.encode(parametersList.toString(), "UTF-8")

            var signature: String? = null
            try {
                val signingKey = SecretKeySpec("$consumerSecret&".toByteArray(), "HmacSHA1")
                val mac = Mac.getInstance("HmacSHA1")
                mac.init(signingKey)
                val rawHMAC = mac.doFinal(signatureString.toByteArray())
                val encoder = Base64.getEncoder()
                signature = encoder.encodeToString(rawHMAC)
            } catch (e: Exception) {
                System.err.println("Unable to append signature")
            }

            return "OAuth " +
                    "oauth_consumer_key=\"" + consumerKey + "\", " +
                    "oauth_nonce=\"" + oauthNonce + "\", " +
                    "oauth_timestamp=\"" + timestamp + "\", " +
                    "oauth_signature_method=\"HMAC-SHA1\", " +
                    "oauth_signature=\"" + signature + "\", " +
                    "oauth_version=\"1.0\""
        }
    }


}


data class WeatherRetrofit(
        @SerializedName("current_observation")
        val currentObservation: CurrentObservation? = CurrentObservation(),
        @SerializedName("forecasts")
        val forecasts: List<Forecast> = listOf(),
        @SerializedName("location")
        val location: Location? = Location()
) {
    data class CurrentObservation(
            @SerializedName("astronomy")
            val astronomy: Astronomy? = Astronomy(),
            @SerializedName("atmosphere")
            val atmosphere: Atmosphere? = Atmosphere(),
            @SerializedName("condition")
            val condition: Condition? = Condition(),
            @SerializedName("pubDate")
            val pubDate: Int? = 0, // 1554908400
            @SerializedName("wind")
            val wind: Wind? = Wind()
    ) {
        data class Astronomy(
                @SerializedName("sunrise")
                val sunrise: String? = "", // 6:41 am
                @SerializedName("sunset")
                val sunset: String? = "" // 7:39 pm
        )

        data class Wind(
                @SerializedName("chill")
                val chill: Int? = 0, // 48
                @SerializedName("direction")
                val direction: Int? = 0, // 305
                @SerializedName("speed")
                val speed: Double? = 0.0 // 8.7
        )

        data class Condition(
                @SerializedName("code")
                val code: Int? = 0, // 32
                @SerializedName("temperature")
                val temperature: Int? = 0, // 52
                @SerializedName("text")
                val text: String? = "" // Sunny
        )

        data class Atmosphere(
                @SerializedName("humidity")
                val humidity: Int? = 0, // 72
                @SerializedName("pressure")
                val pressure: Double? = 0.0, // 30.06
                @SerializedName("rising")
                val rising: Int? = 0, // 0
                @SerializedName("visibility")
                val visibility: Int? = 0 // 10
        )
    }

    data class Forecast(
            @SerializedName("code")
            val code: Int? = 0, // 30
            @SerializedName("date")
            val date: Int? = 0, // 1555657200
            @SerializedName("day")
            val day: String? = "", // Fri
            @SerializedName("high")
            val high: Int? = 0, // 77
            @SerializedName("low")
            val low: Int? = 0, // 57
            @SerializedName("text")
            val text: String? = "" // Partly Cloudy
    )

    data class Location(
            @SerializedName("city")
            val city: String? = "", // Sunnyvale
            @SerializedName("country")
            val country: String? = "", // United States
            @SerializedName("lat")
            val lat: Double? = 0.0, // 37.371609
            @SerializedName("long")
            val long: Double? = 0.0, // -122.038254
            @SerializedName("region")
            val region: String? = "", // CA
            @SerializedName("timezone_id")
            val timezoneId: String? = "", // America/Los_Angeles
            @SerializedName("woeid")
            val woeid: Int? = 0 // 2502265
    )
}

package droid.maxaria.maxander.mvvmweather.buissnes.api

import androidx.lifecycle.LiveData
import droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface WeatherApi {
    @GET("data/2.5/onecall?")
    suspend fun getWeather(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("exclude") exclude:String="minutely,alerts",
        @Query("appid") appid:String="f8521b87a446d9937ff62444bee5e957",
        @Query("lang")  lang:String="en"
    ): Response<WeatherModel>
}
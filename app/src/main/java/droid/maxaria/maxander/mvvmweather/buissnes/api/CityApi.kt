package droid.maxaria.maxander.mvvmweather.buissnes.api

import androidx.lifecycle.LiveData
import droid.maxaria.maxander.mvvmweather.buissnes.api.city_model.CityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {
    @GET("geo/1.0/reverse?")
    suspend fun getCity(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("limit") exclude:String="10",
        @Query("appid") appid:String="f8521b87a446d9937ff62444bee5e957"
    ):Response<CityModel>
}
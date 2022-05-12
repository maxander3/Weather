package droid.maxaria.maxander.mvvmweather.buissnes.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiProvider {
    private val openWeatherMap:Retrofit by lazy { initApi() }
    private fun initApi() = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getWeatherApi():WeatherApi=openWeatherMap.create(WeatherApi::class.java)
    fun getCityApi():CityApi= openWeatherMap.create(CityApi::class.java)
}
package droid.maxaria.maxander.mvvmweather.repository


import droid.maxaria.maxander.mvvmweather.buissnes.api.ApiProvider

class MyRepository(private val api: ApiProvider) {
    suspend fun getWeather(lat:String,lon:String)=api.getWeatherApi().getWeather(lat =lat, lon =lon)
    suspend fun getCityName(lat:String,lon:String)=api.getCityApi().getCity(lat =lat, lon =lon)
}
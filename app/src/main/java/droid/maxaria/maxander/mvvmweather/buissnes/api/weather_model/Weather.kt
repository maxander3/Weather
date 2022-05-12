package droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
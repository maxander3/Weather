package droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model

data class WeatherModel(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)
package droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)
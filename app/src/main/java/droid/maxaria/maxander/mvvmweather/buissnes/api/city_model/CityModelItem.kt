package droid.maxaria.maxander.mvvmweather.buissnes.api.city_model

data class CityModelItem(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)
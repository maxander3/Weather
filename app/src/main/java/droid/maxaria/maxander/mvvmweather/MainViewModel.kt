package droid.maxaria.maxander.mvvmweather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import droid.maxaria.maxander.mvvmweather.buissnes.api.ApiProvider
import droid.maxaria.maxander.mvvmweather.buissnes.api.city_model.CityModel
import droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model.WeatherModel
import droid.maxaria.maxander.mvvmweather.repository.MyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
const val TAG="viewModel"
class MainViewModel():ViewModel() {
    val cityName=MutableLiveData<CityModel>()
    val weather=MutableLiveData<WeatherModel>()
    private val rep by lazy{MyRepository(ApiProvider())}
     fun refresh(lat:String,lon:String){

        CoroutineScope(Dispatchers.IO).launch {
            val weatherResponse=rep.getWeather(lat =lat, lon =lon)
            val cityResponse=rep.getCityName(lat =lat, lon =lon)
            cityName.postValue(cityResponse.body())
            weather.postValue(weatherResponse.body())
        }
    }
}
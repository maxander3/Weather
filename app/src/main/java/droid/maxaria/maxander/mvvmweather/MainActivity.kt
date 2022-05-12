package droid.maxaria.maxander.mvvmweather

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import droid.maxaria.maxander.mvvmweather.buissnes.api.city_model.CityModel
import droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model.Current
import droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model.WeatherModel
import droid.maxaria.maxander.mvvmweather.databinding.ActivityMainBinding
import droid.maxaria.maxander.mvvmweather.utils.*
import droid.maxaria.maxander.mvvmweather.view.DailyAdapter
import droid.maxaria.maxander.mvvmweather.view.HourlyAdapter

class MainActivity : AppCompatActivity() {

    private var _viewModel: MainViewModel? = null
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var dailyRecyclerView:RecyclerView
    private lateinit var dailyAdapter:DailyAdapter
    private lateinit var hourlyRecyclerView: RecyclerView
    private lateinit var hourlyAdapter: HourlyAdapter
    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private val mViewModel
        get() = _viewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

    }

    override fun onStart() {
        dailyAdapter= DailyAdapter()
        hourlyAdapter= HourlyAdapter()
        dailyRecyclerView=mBinding.mainDailyList
        hourlyRecyclerView=mBinding.mainHourlyList
        dailyRecyclerView.adapter=dailyAdapter
        hourlyRecyclerView.adapter=hourlyAdapter
        dailyRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        hourlyRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        checkPermission()
        geoService.requestLocationUpdates(locationRequest, geoCallback, mainLooper)
        mViewModel.cityName.observe(this) { it ->
            val cityName = it.asSequence().map { it.name }
                .filterNotNull()
                .first()
            initCurrentCity(cityName)
        }
        mViewModel.weather.observe(this) {
            initCurrentData(it)
            dailyAdapter.setDailyList(it.daily)
            hourlyAdapter.setHourlyList(it.hourly)
        }
        super.onStart()
    }

    override fun onDestroy() {
        mViewModel.weather.removeObservers(this)
        mViewModel.weather.removeObservers(this)
        _viewModel = null
        dailyRecyclerView.adapter=null
        super.onDestroy()
    }


    @SuppressLint("SetTextI18n")
    private fun initCurrentData(data: WeatherModel) {
        data.current.apply {
            mBinding.mainDateTv.text = dt.toDateFormatOf(DAY_FULL_MONTH_NAME)
            mBinding.mainSunsetMuTv.text = sunset.toDateFormatOf(HOUR_DOUBLE_DOT_MINUTE)
            mBinding.mainSunriseMuTv.text = sunrise.toDateFormatOf(HOUR_DOUBLE_DOT_MINUTE)
            mBinding.mainHumidityMuTv.text = "$humidity %"
            mBinding.mainTemp.text = temp.toDegree()+"\u00B0"
            mBinding.mainWindSpeedMuTv.text = wind_speed.toString()+" m/s"
            mBinding.mainPressureMuTv.text = (pressure-250).toString()+" mm"
            mBinding.mainWeatherConditionDescription.text=weather[0].description
            mBinding.mainWeatherImg.setImageResource(provideMainIcon(weather[0].icon))
            mBinding.mainWeatherConditionIcon.setImageResource(provideSmallIcon(weather[0].icon))
        }
        data.daily[0].temp.apply {
            mBinding.mainTempMinTv.text=min.toDegree()+"\u00B0"
            mBinding.mainTempMaxTv.text=max.toDegree()+"\u00B0"
        }

    }

    private fun initCurrentCity(data: String) {
        mBinding.mainCityNameTv.text = data
    }

    private val geoCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (location in p0.locations) {
                val mLocation = location
                Log.d("TAG", "${mLocation.latitude},${mLocation.longitude}")
                mViewModel.refresh(mLocation.latitude.toString(), mLocation.longitude.toString())
            }
        }
    }


    private fun initLocationRequest(): LocationRequest {
        val request = LocationRequest.create()
        return request.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        }

    }
}
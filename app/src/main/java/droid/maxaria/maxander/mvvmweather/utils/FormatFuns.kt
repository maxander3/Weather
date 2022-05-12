package droid.maxaria.maxander.mvvmweather.utils

import droid.maxaria.maxander.mvvmweather.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

const val DAY_FULL_MONTH_NAME = "dd MMMM"
const val DAY_WEEK_NAME_LONG = "dd EEEE"
const val HOUR_DOUBLE_DOT_MINUTE = "HH:mm"

fun Long.toDateFormatOf(format: String): String {
    val cal = Calendar.getInstance()
    val timeZone = cal.timeZone
    val sdf = SimpleDateFormat(format, Locale.US)
    sdf.timeZone = timeZone
    var returner = sdf.format(Date(this * 1000))
    if (returner[0] == '0')
        returner = returner.drop(1)
    return returner
}

fun Double.toDegree() = (this - 273.15).roundToInt().toString()
fun Double.toPercent() = (this*100).roundToInt().toString()
fun provideMainIcon(icon: String): Int {
    return when (icon) {
        "01d" -> R.mipmap.sun_main_icon
        "01n" -> R.mipmap.moon_main_icon
        "02d" -> R.mipmap.sun_fewclouds_icon
        "02n" -> R.mipmap.moon_main_fewclouds_icon
        "03d" -> R.mipmap.clouds_icon
        "03n" -> R.mipmap.clouds_icon
        "04d" -> R.mipmap.clouds_icon
        "04n" -> R.mipmap.clouds_icon
        "09d" -> R.mipmap.rain_icon
        "09n" -> R.mipmap.rain_icon
        "10d" -> R.mipmap.sun_rain_icon
        "10n" -> R.mipmap.moon_rain_icon
        "11d" -> R.mipmap.storm_icon
        "11n" -> R.mipmap.storm_icon
        "13d" -> R.mipmap.snow_icon
        "13n" -> R.mipmap.snow_icon
        "50d" -> R.mipmap.mist_icon
        "50n" -> R.mipmap.mist_icon
        else -> R.mipmap.error_icon
    }
}
fun provideSmallIcon(icon: String): Int {
    return when (icon) {
        "01d" -> R.mipmap.sun_main_icon
        "01n" -> R.mipmap.moon_small_icon
        "02d" -> R.mipmap.sun_clouds_small_icon
        "02n" -> R.mipmap.moon_clouds_small_icon
        "03d" -> R.mipmap.clouds_small_icon
        "03n" -> R.mipmap.clouds_small_icon
        "04d" -> R.mipmap.clouds_small_icon
        "04n" -> R.mipmap.clouds_small_icon
        "09d" -> R.mipmap.rain_small_icon
        "09n" -> R.mipmap.rain_small_icon
        "10d" -> R.mipmap.rain_small_icon
        "10n" -> R.mipmap.rain_small_icon
        "11d" -> R.mipmap.storm_small_icon
        "11n" -> R.mipmap.storm_small_icon
        "13d" -> R.mipmap.snow_small_icon
        "13n" -> R.mipmap.snow_small_icon
        "50d" -> R.mipmap.mist_small_icon
        "50n" -> R.mipmap.mist_small_icon
        else -> R.mipmap.error_small_icon
    }
}

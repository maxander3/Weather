package droid.maxaria.maxander.mvvmweather.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model.Hourly
import droid.maxaria.maxander.mvvmweather.databinding.ItemMainHourlyBinding
import droid.maxaria.maxander.mvvmweather.utils.*

class HourlyAdapter: RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {
    private var _list= emptyList<Hourly>()
    private val mList
            get() = _list

    inner class HourlyViewHolder(val binding: ItemMainHourlyBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        return HourlyViewHolder(ItemMainHourlyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.binding.apply {
            itemHourlyPopTv.text=mList[position].pop.toPercent()+"%"
            itemHourlyTempTv.text=mList[position].temp.toDegree()+"\u00B0"
            itemHourlyTimeTv.text=mList[position].dt.toDateFormatOf(HOUR_DOUBLE_DOT_MINUTE)
            itemHourlyWeatherConditionIcon.setImageResource(provideSmallIcon(mList[position].weather[0].icon))
        }
    }

    override fun getItemCount()=mList.size
    fun setHourlyList(list:List<Hourly>){
        _list=list
        notifyDataSetChanged()
    }
}
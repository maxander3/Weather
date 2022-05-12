package droid.maxaria.maxander.mvvmweather.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import droid.maxaria.maxander.mvvmweather.MainViewModel
import droid.maxaria.maxander.mvvmweather.R
import droid.maxaria.maxander.mvvmweather.buissnes.api.weather_model.Daily
import droid.maxaria.maxander.mvvmweather.databinding.ItemMainDailyBinding
import droid.maxaria.maxander.mvvmweather.utils.*

class DailyAdapter(): RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {
    private var _mList:List<Daily> = emptyList()
    protected val mList: List<Daily>
        get() = _mList!!



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        //another binding
        val binding= ItemMainDailyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DailyViewHolder(binding)
    }

    inner class DailyViewHolder(val binding: ItemMainDailyBinding):RecyclerView.ViewHolder(binding.root){
        val txtDT:TextView=binding.itemDailyDateTv
        val minTemp:TextView=binding.itemDailyMinTempTv
        val maxTemp:TextView=binding.itemDailyMaxTempTv
        val pop:TextView=binding.itemDailyPopTv
        val icon:ImageView=binding.itemDailyWeatherConditionIcon
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.apply {
            txtDT.text=mList[position].dt.toDateFormatOf(DAY_FULL_MONTH_NAME)
            minTemp.text=mList[position].temp.min.toDegree()+"\u00B0"
            maxTemp.text=mList[position].temp.max.toDegree()+"\u00B0"
            pop.text=mList[position].pop.toPercent()+" %"
            icon.setImageResource(provideSmallIcon(mList[position].weather[0].icon))
        }
    }

    override fun getItemCount()=mList.size
    fun setDailyList(list:List<Daily>){
        _mList=list
        notifyDataSetChanged()
    }
}





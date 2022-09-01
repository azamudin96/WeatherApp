package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeatherItemBinding
import com.example.weatherapp.models.Forecastday
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.utils.DateUtils
import com.example.weatherapp.utils.WeatherUtil
import java.text.DecimalFormat

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var data = WeatherResponse()

    var arrayData = mutableListOf<Forecastday>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
  
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view 
        // that is used to hold list item
        val inflater = LayoutInflater.from(parent.context)
        val view = WeatherItemBinding.inflate(inflater,parent,false)
  
        return ViewHolder(view)
    }
  
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayData[position]
        holder.onBind(position,data,item)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return arrayData.size
    }
  
    // Holds the views for adding it to image and text
    inner class ViewHolder internal constructor(private val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int,data: WeatherResponse,item:Forecastday){
            binding.txtCity.text = data.location?.name

            val dateTime = DateUtils.formateDate(
                "${item.date}",
                "yyyy-MM-dd",
                "EEEE, MMM dd"
            )

            binding.txtDateTime.text = dateTime
            binding.txtCloud.text = item.day?.condition?.text
            binding.txtTemp.text = DecimalFormat("#").format(item.day?.avgtempC)
            binding.txtTempSymbol.text = "\u2103"
            binding.txtWillRain.text = "${item.day?.dailyChanceOfRain} %"

            val code = item.day?.condition?.code
            binding.imgWeather.setImageResource(WeatherUtil.getWeatherIcon(code!!))
        }
    }
}
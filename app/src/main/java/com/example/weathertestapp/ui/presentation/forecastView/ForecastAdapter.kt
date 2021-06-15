package com.example.weathertestapp.ui.presentation.forecastView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.data.model.Model
import com.example.weathertestapp.databinding.ItemHolderBinding
import com.example.weathertestapp.firstToUpperCase
import com.example.weathertestapp.toWeatherIcon

class ForecastAdapter :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(val binding: ItemHolderBinding) : RecyclerView.ViewHolder(binding.root)

    private var forecastList = emptyList<Model>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding =
            ItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val currentForecast = forecastList[position]
        holder.binding.ivIcon.setImageResource(currentForecast.weather[0].icon.toWeatherIcon())
        holder.binding.tvTime.text = currentForecast.dt_txt.substring(11, 16)
        holder.binding.tvDescr.text = currentForecast.weather[0].description.firstToUpperCase()
        holder.binding.tvTempr.text = (currentForecast.main.temp-273.15).toInt().toString() +"°С"
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun setData(forecast: List<Model>) {
        this.forecastList = forecast
        notifyDataSetChanged()
    }

}
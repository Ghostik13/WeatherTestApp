package com.example.weathertestapp.ui.presentation.forecastView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.R
import com.example.weathertestapp.data.model.Model
import com.example.weathertestapp.data.model.ModelDb
import com.example.weathertestapp.databinding.ItemHolderBinding
import com.example.weathertestapp.firstToUpperCase
import com.example.weathertestapp.toWeatherIcon

class ForecastAdapter :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(val binding: ItemHolderBinding) : RecyclerView.ViewHolder(binding.root)

    private var forecastList = emptyList<ModelDb>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding =
            ItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val currentForecast = forecastList[position]
        with(holder.binding) {
            ivIcon.setImageResource(currentForecast.icon.toWeatherIcon())
            tvTime.text = currentForecast.dt_txt.substring(11, 16)
            tvDescr.text = currentForecast.description.firstToUpperCase()
            tvTempr.text = (currentForecast.temp - 273.15).toInt().toString() + "°С"
        }
        if (position == 0) {
            holder.binding.viewHolder.setBackgroundResource(R.drawable.holder_borders)
        }
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun setData(forecast: List<ModelDb>) {
        this.forecastList = forecast
        notifyDataSetChanged()
    }

}
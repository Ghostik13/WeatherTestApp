package com.example.weathertestapp.ui.presentation.currentWeatherView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weathertestapp.*
import com.example.weathertestapp.databinding.FragmentCurrentWeatherBinding
import com.example.weathertestapp.ui.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        initWeather(binding)
        initShare(binding)
        return view
    }

    private fun initShare(view: FragmentCurrentWeatherBinding) {
        view.shareBtn.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra("Temp", binding.tvTemp.text.toString())
            sendIntent.putExtra("Descr", binding.tvDesc.text.toString())
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    private fun initWeather(view: FragmentCurrentWeatherBinding) {
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it == false) {
                view.pb.visibility = View.VISIBLE
            } else {
                view.pb.visibility = View.GONE
            }
        })
        viewModel.getCurrentWeatherDb()
        viewModel.city.observe(viewLifecycleOwner, { city ->
            viewModel.getCurrentWeather(city)
        })
        viewModel.weather.observe(viewLifecycleOwner, {
            it?.let {
                view.tvTemp.text = getString(R.string.celcius, it.temp.toString())
                view.tvDesc.text = it.description.firstToUpperCase()
                view.tvHum.text = getString(R.string.humidity, it.humidity.toString())
                if (it.rain != null) {
                    view.tvRain.text = it.rain.toString()
                } else {
                    view.tvRain.text = getString(R.string.rain)
                }
                view.tvPressure.text = getString(R.string.pressure, it.pressure.toString())
                view.tvSpeed.text = getString(R.string.speed, it.windSpeed.toString())
                view.tvDir.text = it.windDirection
                view.tvCity.text = it.city
                view.ivWeather.setImageResource(it.icon)
            }
        })
    }
}
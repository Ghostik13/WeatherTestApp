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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
        initWeather()
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

    private fun initWeather() {
        viewModel.loading.observe(viewLifecycleOwner, {
            binding.pb.visibility = if (it == false) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
        viewModel.getCurrentWeatherDb()
        viewModel.city.observe(viewLifecycleOwner, { city ->
            GlobalScope.launch { viewModel.getCurrentWeather(city) }
        })
        viewModel.weather.observe(viewLifecycleOwner, {
            it?.let {
                with(binding) {
                    tvTemp.text = it.temp
                    tvDesc.text = it.description.firstToUpperCase()
                    tvHum.text = getString(R.string.humidity, it.humidity.toString())
                }
                if (it.rain != null) {
                    binding.tvRain.text = it.rain.toString()
                } else {
                    binding.tvRain.text = getString(R.string.rain)
                }
                with(binding) {
                    tvPressure.text = getString(R.string.pressure, it.pressure.toString())
                    tvSpeed.text = it.windSpeed
                    tvDir.text = it.windDirection
                    tvCity.text = it.city
                    ivWeather.setImageResource(it.icon)
                }
            }
        })
    }
}
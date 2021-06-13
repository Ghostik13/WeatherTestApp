package com.example.weathertestapp.ui.presentation.currentWeatherView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.FragmentCurrentWeatherBinding
import com.example.weathertestapp.firstToUpperCase
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrentWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel.getCurrentWeather()
        viewModel.weather.observe(viewLifecycleOwner, Observer {
            binding.tvTemp.text = getString(R.string.celcius, it.temp.toString())
            binding.tvDesc.text = it.description.firstToUpperCase()
        })
        return view
    }

}
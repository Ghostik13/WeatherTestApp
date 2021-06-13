package com.example.weathertestapp.ui.presentation.currentWeatherView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathertestapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : Fragment() {

    private val viewModel: CurrentWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        return view
    }

}
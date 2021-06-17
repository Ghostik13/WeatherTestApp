package com.example.weathertestapp.ui.presentation.forecastView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.databinding.FragmentForecastBinding
import com.example.weathertestapp.ui.presentation.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        initForecast(binding)
        return binding.root
    }

    private fun initForecast(view: FragmentForecastBinding) {
        recyclerView = view.recyclerView
        val forecastAdapter = ForecastAdapter()
        recyclerView.adapter = forecastAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it == false) {
                view.pbForecast.visibility = View.VISIBLE
            } else {
                view.pbForecast.visibility = View.GONE
            }
        })
        viewModel.getForecastDb()
        viewModel.city.observe(viewLifecycleOwner, { city ->
            GlobalScope.launch {
                viewModel.getForecast(city)
            }
            view.tvToday.text = city
        })
        viewModel.forecast.observe(viewLifecycleOwner, { forecast ->
            forecast?.list?.let { forecastAdapter.setData(it) }
        })
    }
}
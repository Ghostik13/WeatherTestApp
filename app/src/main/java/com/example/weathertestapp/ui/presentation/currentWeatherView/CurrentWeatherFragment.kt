package com.example.weathertestapp.ui.presentation.currentWeatherView

import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weathertestapp.*
import com.example.weathertestapp.databinding.FragmentCurrentWeatherBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CurrentWeatherFragment : Fragment(), LocationListener {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrentWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        initWeather(binding)
        initShare(binding)
        getLocation()
        return view
    }

    private lateinit var locationManager: LocationManager

    private fun getLocation() {
        try {
            locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                5f,
                this
            )
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                5f,
                this
            )
        } catch (e: SecurityException) {
            Log.d("SECURITY", "DENIED")
        }
    }

    private fun initShare(view: FragmentCurrentWeatherBinding) {
        view.shareBtn.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, binding.tvTemp.text.toString())
            sendIntent.putExtra(Intent.EXTRA_TEXT, binding.tvDesc.text.toString())
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    private fun initWeather(view: FragmentCurrentWeatherBinding) {
        viewModel.getCurrentWeather()
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it == false) {
                view.pb.visibility = View.VISIBLE
            } else {
                view.pb.visibility = View.GONE
            }
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

    override fun onLocationChanged(location: Location) {
        if (isAdded) {
            val addresses: List<Address>
            val geoCoder = Geocoder(requireContext(), Locale.getDefault())
            addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            val city = addresses[0].locality.toString()
            viewModel.city.value = city
        }

    }
}
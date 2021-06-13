package com.example.weathertestapp.ui.presentation.currentWeatherView

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weathertestapp.Constants
import com.example.weathertestapp.MainActivity
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.FragmentCurrentWeatherBinding
import com.example.weathertestapp.firstToUpperCase
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
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    MainActivity(),
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), Constants.LOCATION_REQUEST
                )
            } else {
                ActivityCompat.requestPermissions(
                    MainActivity(),
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), Constants.LOCATION_REQUEST
                )
            }
        } else {
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
        }
    }

    private fun initShare(view: FragmentCurrentWeatherBinding) {
        view.tvShare.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, binding.tvTemp.text.toString())
            sendIntent.putExtra(Intent.EXTRA_TEXT, binding.tvDesc.text.toString())
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    private fun initWeather(view: FragmentCurrentWeatherBinding) {
        viewModel.getCurrentWeather()
        viewModel.weather.observe(viewLifecycleOwner, {
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
        })
    }

    override fun onLocationChanged(location: Location) {
        val addresses: List<Address>
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        val city = addresses[0].locality.toString()
        binding.tvCity.text = city
        viewModel.city.value = city
    }
}
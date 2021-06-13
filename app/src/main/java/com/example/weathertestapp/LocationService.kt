package com.example.weathertestapp

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.location.Location
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weathertestapp.Constants.Companion.LOCATION_REQUEST
import java.util.*

class LocationService: Service() {

    lateinit var cityName: String

    private lateinit var locationManager: LocationManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        locationManager = applicationContext.getSystemService(LocationManager::class.java)
        checkPermission()
        return START_NOT_STICKY
    }

    private val locationListener: LocationListener =
        LocationListener { location ->
            showLocation(location)
        }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                applicationContext,
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
                    ), LOCATION_REQUEST
                )
            } else {
                ActivityCompat.requestPermissions(
                    MainActivity(),
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), LOCATION_REQUEST
                )
            }
        } else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                30000,
                2f,
                locationListener
            )
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                30000,
                2f,
                locationListener
            )
        }
    }

    private fun getCityName(address: String): String {
        return address
    }

    private fun getGeo(location: Location): String {
        val addresses: List<Address>
        val geoCoder = Geocoder(applicationContext, Locale.getDefault())
        addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        return addresses[0].locality.toString()
    }

    private fun showLocation(location: Location) {
        if (location.provider.equals(LocationManager.GPS_PROVIDER)) {
            cityName = getCityName(getGeo(location))
            Log.d("CITY:", cityName)
        } else if (location.provider.equals(
                LocationManager.NETWORK_PROVIDER
            )
        ) {
            cityName = getCityName(getGeo(location))
            Log.d("CITY:", cityName)
        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
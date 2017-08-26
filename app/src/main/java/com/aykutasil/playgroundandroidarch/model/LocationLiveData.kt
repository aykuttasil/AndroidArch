package com.aykutasil.playgroundandroidarch.model

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle


/**
 * Created by aykutasil on 26.08.2017.
 */
class LocationLiveData private constructor() : LiveData<Location>() {

    private var locationManager: LocationManager? = null

    companion object {
        var sInstance: LocationLiveData? = null

        fun get(context: Context): LocationLiveData {
            if (sInstance == null) {
                sInstance = LocationLiveData(context)
            }
            return sInstance as LocationLiveData
        }
    }

    private constructor(context: Context) : this() {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val listener = object : LocationListener {
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }

        override fun onLocationChanged(loc: Location?) {
            value = loc
        }

    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        println("onActive")
        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 0f, listener)
    }

    @SuppressLint("MissingPermission")
    override fun onInactive() {
        println("onInactive")
        locationManager?.removeUpdates(listener)
    }

}
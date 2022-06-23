package it.localhostsoftware.maps

import android.location.Location

interface LocationSource {
    fun activate(var1: OnLocationChangedListener)
    fun deactivate()
    fun interface OnLocationChangedListener {
        fun onLocationChanged(var1: Location)
    }
}
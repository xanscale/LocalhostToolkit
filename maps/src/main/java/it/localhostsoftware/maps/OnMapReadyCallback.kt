package it.localhostsoftware.maps

interface OnMapReadyCallback {
    fun onMapReady(geoMap: GeoMap<*>)
}
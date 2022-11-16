package it.localhostsoftware.maps.model

abstract class CameraPosition<CP, LL : LatLng<*>>(val cp: CP) {
    abstract val target: LL
    abstract val zoom: Float
    abstract val tilt: Float
    abstract val bearing: Float
}
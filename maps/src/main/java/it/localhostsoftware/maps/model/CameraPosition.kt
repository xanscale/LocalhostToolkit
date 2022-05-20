package it.localhostsoftware.maps.model

abstract class CameraPosition<CP>(val cp: CP) {
    abstract val target: LatLng<*>
    abstract val zoom: Float
    abstract val tilt: Float
    abstract val bearing: Float
}
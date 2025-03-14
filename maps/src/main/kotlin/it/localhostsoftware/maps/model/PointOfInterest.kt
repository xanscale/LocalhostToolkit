package it.localhostsoftware.maps.model

abstract class PointOfInterest<POI, LL : LatLng<*>>(val poi: POI) {
    abstract val latLng: LL
    abstract val placeId: String
    abstract val name: String
}
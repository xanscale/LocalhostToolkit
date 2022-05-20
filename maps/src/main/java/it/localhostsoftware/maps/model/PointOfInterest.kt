package it.localhostsoftware.maps.model

abstract class PointOfInterest<POI>(val poi: POI) {
    abstract val latLng: LatLng<*>
    abstract val placeId: String
    abstract val name: String
}
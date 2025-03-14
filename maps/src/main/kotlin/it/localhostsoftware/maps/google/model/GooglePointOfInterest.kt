package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.PointOfInterest

class GooglePointOfInterest(poi: PointOfInterest) : it.localhostsoftware.maps.model.PointOfInterest<PointOfInterest, GoogleLatLng>(poi) {
    override val latLng: GoogleLatLng
        get() = GoogleLatLng(poi.latLng)
    override val placeId: String
        get() = poi.placeId
    override val name: String
        get() = poi.name
}
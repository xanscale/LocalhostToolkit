package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.PointOfInterest
import it.localhostsoftware.maps.model.LatLng

class GooglePointOfInterest(poi: PointOfInterest) : it.localhostsoftware.maps.model.PointOfInterest<PointOfInterest>(poi) {
    override val latLng: LatLng<*>
        get() = GoogleLatLng(poi.latLng)
    override val placeId: String
        get() = poi.placeId
    override val name: String
        get() = poi.name
}
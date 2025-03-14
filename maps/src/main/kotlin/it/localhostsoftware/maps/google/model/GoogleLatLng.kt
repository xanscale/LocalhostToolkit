package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.LatLng

class GoogleLatLng(latLng: LatLng) : it.localhostsoftware.maps.model.LatLng<LatLng>(latLng) {
    override val latitude: Double
        get() = ll.latitude
    override val longitude: Double
        get() = ll.longitude
}
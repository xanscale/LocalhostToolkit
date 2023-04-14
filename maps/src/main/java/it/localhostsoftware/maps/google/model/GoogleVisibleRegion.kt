package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.VisibleRegion

class GoogleVisibleRegion(VR: VisibleRegion) : it.localhostsoftware.maps.model.VisibleRegion<VisibleRegion, GoogleLatLng, GoogleLatLngBounds>(VR) {
    override val nearLeft: GoogleLatLng
        get() = GoogleLatLng(vr.nearLeft)
    override val nearRight: GoogleLatLng
        get() = GoogleLatLng(vr.nearRight)
    override val farLeft: GoogleLatLng
        get() = GoogleLatLng(vr.farLeft)
    override val farRight: GoogleLatLng
        get() = GoogleLatLng(vr.farRight)
    override val latLngBounds: GoogleLatLngBounds
        get() = GoogleLatLngBounds(vr.latLngBounds)

    override fun hashCode(): Int {
        return vr.hashCode()
    }

    override fun equals(other: Any?): Boolean = vr == other
    override fun toString(): String = vr.toString()
}
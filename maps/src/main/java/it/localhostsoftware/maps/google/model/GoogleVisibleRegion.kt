package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.VisibleRegion
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.LatLngBounds

class GoogleVisibleRegion(VR: VisibleRegion) : it.localhostsoftware.maps.model.VisibleRegion<VisibleRegion>(VR) {
    override val nearLeft: LatLng<*>
        get() = GoogleLatLng(vr.nearLeft)
    override val nearRight: LatLng<*>
        get() = GoogleLatLng(vr.nearRight)
    override val farLeft: LatLng<*>
        get() = GoogleLatLng(vr.farLeft)
    override val farRight: LatLng<*>
        get() = GoogleLatLng(vr.farRight)
    override val latLngBounds: LatLngBounds<*>
        get() = GoogleLatLngBounds(vr.latLngBounds)

    override fun hashCode(): Int {
        return vr.hashCode()
    }

    override fun equals(other: Any?): Boolean = vr == other
    override fun toString(): String = vr.toString()
}
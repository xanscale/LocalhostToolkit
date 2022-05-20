package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.VisibleRegion
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.LatLngBounds

class HuaweiVisibleRegion(VR: VisibleRegion) : it.localhostsoftware.maps.model.VisibleRegion<VisibleRegion>(VR) {
    override val nearLeft: LatLng<*>
        get() = HuaweiLatLng(vr.nearLeft)
    override val nearRight: LatLng<*>
        get() = HuaweiLatLng(vr.nearRight)
    override val farLeft: LatLng<*>
        get() = HuaweiLatLng(vr.farLeft)
    override val farRight: LatLng<*>
        get() = HuaweiLatLng(vr.farRight)
    override val latLngBounds: LatLngBounds<*>
        get() = HuaweiLatLngBounds(vr.latLngBounds)

    override fun hashCode(): Int {
        return vr.hashCode()
    }

    override fun equals(other: Any?): Boolean = vr == other
    override fun toString(): String = vr.toString()
}
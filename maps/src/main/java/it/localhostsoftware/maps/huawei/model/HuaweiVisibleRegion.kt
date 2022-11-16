package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.VisibleRegion

class HuaweiVisibleRegion(VR: VisibleRegion) : it.localhostsoftware.maps.model.VisibleRegion<VisibleRegion, HuaweiLatLng, HuaweiLatLngBounds>(VR) {
    override val nearLeft: HuaweiLatLng
        get() = HuaweiLatLng(vr.nearLeft)
    override val nearRight: HuaweiLatLng
        get() = HuaweiLatLng(vr.nearRight)
    override val farLeft: HuaweiLatLng
        get() = HuaweiLatLng(vr.farLeft)
    override val farRight: HuaweiLatLng
        get() = HuaweiLatLng(vr.farRight)
    override val latLngBounds: HuaweiLatLngBounds
        get() = HuaweiLatLngBounds(vr.latLngBounds)

    override fun hashCode(): Int {
        return vr.hashCode()
    }

    override fun equals(other: Any?): Boolean = vr == other
    override fun toString(): String = vr.toString()
}
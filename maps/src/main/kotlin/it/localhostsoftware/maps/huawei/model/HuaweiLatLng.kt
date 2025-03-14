package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.LatLng

class HuaweiLatLng(ll: LatLng) : it.localhostsoftware.maps.model.LatLng<LatLng>(ll) {
    override val latitude: Double
        get() = ll.latitude
    override val longitude: Double
        get() = ll.longitude
}
package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.PointOfInterest

class HuaweiPointOfInterest(POI: PointOfInterest) : it.localhostsoftware.maps.model.PointOfInterest<PointOfInterest, HuaweiLatLng>(POI) {
    override val latLng: HuaweiLatLng
        get() = HuaweiLatLng(poi.latLng)
    override val placeId: String
        get() = poi.placeId
    override val name: String
        get() = poi.name
}
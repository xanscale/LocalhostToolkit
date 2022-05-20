package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.PointOfInterest
import it.localhostsoftware.maps.model.LatLng

class HuaweiPointOfInterest(POI: PointOfInterest) : it.localhostsoftware.maps.model.PointOfInterest<PointOfInterest>(POI) {
    override val latLng: LatLng<*>
        get() = HuaweiLatLng(poi.latLng)
    override val placeId: String
        get() = poi.placeId
    override val name: String
        get() = poi.name
}
package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLng

class HuaweiCameraPosition(cp: CameraPosition) : it.localhostsoftware.maps.model.CameraPosition<CameraPosition>(cp) {
    override val target: LatLng<*>
        get() = HuaweiLatLng(cp.target)
    override val zoom: Float
        get() = cp.zoom
    override val tilt: Float
        get() = cp.tilt
    override val bearing: Float
        get() = cp.bearing
}
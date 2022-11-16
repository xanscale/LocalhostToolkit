package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.CameraPosition

class HuaweiCameraPosition(cp: CameraPosition) : it.localhostsoftware.maps.model.CameraPosition<CameraPosition, HuaweiLatLng>(cp) {
    override val target: HuaweiLatLng
        get() = HuaweiLatLng(cp.target)
    override val zoom: Float
        get() = cp.zoom
    override val tilt: Float
        get() = cp.tilt
    override val bearing: Float
        get() = cp.bearing
}
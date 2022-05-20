package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLng

class GoogleCameraPosition(cameraPosition: CameraPosition) : it.localhostsoftware.maps.model.CameraPosition<CameraPosition>(cameraPosition) {
    override val target: LatLng<*>
        get() = GoogleLatLng(cp.target)
    override val zoom: Float
        get() = cp.zoom
    override val tilt: Float
        get() = cp.tilt
    override val bearing: Float
        get() = cp.bearing
}
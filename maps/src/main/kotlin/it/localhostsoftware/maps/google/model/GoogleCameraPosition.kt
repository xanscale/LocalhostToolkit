package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.CameraPosition

class GoogleCameraPosition(cameraPosition: CameraPosition) : it.localhostsoftware.maps.model.CameraPosition<CameraPosition, GoogleLatLng>(cameraPosition) {
    override val target: GoogleLatLng
        get() = GoogleLatLng(cp.target)
    override val zoom: Float
        get() = cp.zoom
    override val tilt: Float
        get() = cp.tilt
    override val bearing: Float
        get() = cp.bearing
}
package it.localhostsoftware.maps

import android.content.Context
import com.google.android.gms.maps.GoogleMapOptions
import com.huawei.hms.maps.HuaweiMapOptions
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLngBounds

abstract class MapOptions<MO>(val mo: MO) {
    companion object {
        fun getInstance(c: Context) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> it.localhostsoftware.maps.google.GoogleMapOptions(GoogleMapOptions())
                    MobileServices.HUAWEI -> it.localhostsoftware.maps.huawei.HuaweiMapOptions(HuaweiMapOptions())
                    else -> throw IllegalStateException()
                }
    }

    abstract var zOrderOnTop: Boolean?
    abstract var useViewLifecycleInFragment: Boolean?
    abstract var mapType: Int
    abstract var camera: CameraPosition<*>?
    abstract var zoomControlsEnabled: Boolean?
    abstract var compassEnabled: Boolean?
    abstract var scrollGesturesEnabled: Boolean?
    abstract var zoomGesturesEnabled: Boolean?
    abstract var tiltGesturesEnabled: Boolean?
    abstract var rotateGesturesEnabled: Boolean?
    abstract var liteMode: Boolean?
    abstract var mapToolbarEnabled: Boolean?
    abstract var ambientEnabled: Boolean?
    abstract var minZoomPreference: Float?
    abstract var maxZoomPreference: Float?
    abstract var latLngBoundsForCameraTarget: LatLngBounds<*>?
}
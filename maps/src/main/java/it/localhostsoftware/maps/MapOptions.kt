package it.localhostsoftware.maps

import android.content.Context
import it.localhostsoftware.maps.google.GoogleMapOptions
import it.localhostsoftware.maps.huawei.HuaweiMapOptions
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLngBounds

abstract class MapOptions<MO, CP : CameraPosition<*>, LLB : LatLngBounds<*>>(val mo: MO) {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GoogleMapOptions(com.google.android.gms.maps.GoogleMapOptions())
                MobileServices.HUAWEI -> HuaweiMapOptions(com.huawei.hms.maps.HuaweiMapOptions())
                else -> throw IllegalStateException()
            }
    }

    abstract var zOrderOnTop: Boolean?
    abstract var useViewLifecycleInFragment: Boolean?
    abstract var mapType: Int
    abstract var camera: CP?
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
    abstract var latLngBoundsForCameraTarget: LLB?
}
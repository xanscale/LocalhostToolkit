package it.localhostsoftware.maps.google

import com.google.android.gms.maps.GoogleMapOptions
import it.localhostsoftware.maps.MapOptions
import it.localhostsoftware.maps.google.model.GoogleCameraPosition
import it.localhostsoftware.maps.google.model.GoogleLatLngBounds
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLngBounds

class GoogleMapOptions(googleMapOptions: GoogleMapOptions) : MapOptions<GoogleMapOptions>(googleMapOptions) {
    override var zOrderOnTop: Boolean?
        get() = mo.zOrderOnTop
        set(value) {
            value?.let { mo.zOrderOnTop(it) }
        }
    override var useViewLifecycleInFragment: Boolean?
        get() = mo.useViewLifecycleInFragment
        set(value) {
            value?.let { mo.useViewLifecycleInFragment(it) }
        }
    override var mapType: Int
        get() = mo.mapType
        set(value) {
            mo.mapType(value)
        }
    override var camera: CameraPosition<*>?
        get() = mo.camera?.let { GoogleCameraPosition(it) }
        set(value) {
            mo.camera(value?.cp as? com.google.android.gms.maps.model.CameraPosition)
        }
    override var zoomControlsEnabled: Boolean?
        get() = mo.zoomControlsEnabled
        set(value) {
            value?.let { mo.zoomControlsEnabled(it) }
        }
    override var compassEnabled: Boolean?
        get() = mo.compassEnabled
        set(value) {
            value?.let { mo.compassEnabled(it) }
        }
    override var scrollGesturesEnabled: Boolean?
        get() = mo.scrollGesturesEnabled
        set(value) {
            value?.let { mo.scrollGesturesEnabled(it) }
        }
    override var zoomGesturesEnabled: Boolean?
        get() = mo.zoomGesturesEnabled
        set(value) {
            value?.let { mo.zoomGesturesEnabled(it) }
        }
    override var tiltGesturesEnabled: Boolean?
        get() = mo.tiltGesturesEnabled
        set(value) {
            value?.let { mo.tiltGesturesEnabled(it) }
        }
    override var rotateGesturesEnabled: Boolean?
        get() = mo.rotateGesturesEnabled
        set(value) {
            value?.let { mo.rotateGesturesEnabled(it) }
        }
    override var liteMode: Boolean?
        get() = mo.liteMode
        set(value) {
            value?.let { mo.liteMode(it) }
        }
    override var mapToolbarEnabled: Boolean?
        get() = mo.mapToolbarEnabled
        set(value) {
            value?.let { mo.mapToolbarEnabled(it) }
        }
    override var ambientEnabled: Boolean?
        get() = mo.ambientEnabled
        set(value) {
            value?.let { mo.ambientEnabled(it) }
        }
    override var minZoomPreference: Float?
        get() = mo.minZoomPreference
        set(value) {
            value?.let { mo.minZoomPreference(it) }
        }
    override var maxZoomPreference: Float?
        get() = mo.maxZoomPreference
        set(value) {
            value?.let { mo.maxZoomPreference(it) }
        }
    override var latLngBoundsForCameraTarget: LatLngBounds<*>?
        get() = mo.latLngBoundsForCameraTarget?.let { GoogleLatLngBounds(it) }
        set(value) {
            mo.latLngBoundsForCameraTarget(value?.lb as? com.google.android.gms.maps.model.LatLngBounds)
        }
}
package it.localhostsoftware.maps

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.GoogleMapOptions
import com.huawei.hms.api.HuaweiApiAvailability
import com.huawei.hms.maps.HuaweiMapOptions
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLngBounds

abstract class MapOptions<MO>(val mo: MO) {
    companion object {
        fun getInstance(context: Context) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) it.localhostsoftware.maps.google.GoogleMapOptions(GoogleMapOptions())
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) it.localhostsoftware.maps.huawei.HuaweiMapOptions(HuaweiMapOptions())
                else throw IllegalStateException()
    }

    abstract fun zOrderOnTop(var1: Boolean): MapOptions<*>
    abstract fun useViewLifecycleInFragment(var1: Boolean): MapOptions<*>
    abstract fun mapType(var1: Int): MapOptions<*>
    abstract fun camera(var1: CameraPosition<*>?): MapOptions<*>
    abstract fun zoomControlsEnabled(var1: Boolean): MapOptions<*>
    abstract fun compassEnabled(var1: Boolean): MapOptions<*>
    abstract fun scrollGesturesEnabled(var1: Boolean): MapOptions<*>
    abstract fun zoomGesturesEnabled(var1: Boolean): MapOptions<*>
    abstract fun tiltGesturesEnabled(var1: Boolean): MapOptions<*>
    abstract fun rotateGesturesEnabled(var1: Boolean): MapOptions<*>
    abstract fun liteMode(var1: Boolean): MapOptions<*>
    abstract fun mapToolbarEnabled(var1: Boolean): MapOptions<*>
    abstract fun ambientEnabled(var1: Boolean): MapOptions<*>
    abstract fun minZoomPreference(var1: Float): MapOptions<*>
    abstract fun maxZoomPreference(var1: Float): MapOptions<*>
    abstract fun latLngBoundsForCameraTarget(var1: LatLngBounds<*>?): MapOptions<*>
    abstract val zOrderOnTop: Boolean?
    abstract val useViewLifecycleInFragment: Boolean?
    abstract val mapType: Int
    abstract val camera: CameraPosition<*>?
    abstract val zoomControlsEnabled: Boolean?
    abstract val compassEnabled: Boolean?
    abstract val scrollGesturesEnabled: Boolean?
    abstract val zoomGesturesEnabled: Boolean?
    abstract val tiltGesturesEnabled: Boolean?
    abstract val rotateGesturesEnabled: Boolean?
    abstract val liteMode: Boolean?
    abstract val mapToolbarEnabled: Boolean?
    abstract val ambientEnabled: Boolean?
    abstract val minZoomPreference: Float?
    abstract val maxZoomPreference: Float?
    abstract val latLngBoundsForCameraTarget: LatLngBounds<*>?
}
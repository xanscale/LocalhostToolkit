package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.model.GoogleMarkerOptions
import it.localhostsoftware.maps.huawei.model.HuaweiMarkerOptions

abstract class MarkerOptions<MO>(val mo: MO) {
    companion object {
        fun getInstance(context: Context) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GoogleMarkerOptions(com.google.android.gms.maps.model.MarkerOptions())
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiMarkerOptions(com.huawei.hms.maps.model.MarkerOptions())
                else throw IllegalStateException()
    }

    abstract fun position(var1: LatLng<*>): MarkerOptions<*>
    abstract fun zIndex(var1: Float): MarkerOptions<*>
    abstract fun icon(var1: BitmapDescriptor<*>?): MarkerOptions<*>
    abstract fun anchor(var1: Float, var2: Float): MarkerOptions<*>
    abstract fun infoWindowAnchor(var1: Float, var2: Float): MarkerOptions<*>
    abstract fun title(var1: String): MarkerOptions<*>
    abstract fun snippet(var1: String): MarkerOptions<*>
    abstract fun draggable(var1: Boolean): MarkerOptions<*>
    abstract fun visible(var1: Boolean): MarkerOptions<*>
    abstract fun flat(var1: Boolean): MarkerOptions<*>
    abstract fun rotation(var1: Float): MarkerOptions<*>
    abstract fun alpha(var1: Float): MarkerOptions<*>
    abstract val position: LatLng<*>
    abstract val title: String?
    abstract val snippet: String?
    abstract val icon: BitmapDescriptor<*>
    abstract val anchorU: Float
    abstract val anchorV: Float
    abstract val isDraggable: Boolean
    abstract val isVisible: Boolean
    abstract val isFlat: Boolean
    abstract val rotation: Float
    abstract val infoWindowAnchorU: Float
    abstract val infoWindowAnchorV: Float
    abstract val alpha: Float
    abstract val zIndex: Float
}
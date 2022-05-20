package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.model.GoogleCircleOptions
import it.localhostsoftware.maps.huawei.model.HuaweiCircleOptions

abstract class CircleOptions<CO>(val co: CO) {
    companion object {
        fun getInstance(context: Context) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GoogleCircleOptions(com.google.android.gms.maps.model.CircleOptions())
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiCircleOptions(com.huawei.hms.maps.model.CircleOptions())
                else throw IllegalStateException()
    }

    abstract fun center(var1: LatLng<*>): CircleOptions<*>
    abstract fun radius(var1: Double): CircleOptions<*>
    abstract fun strokeWidth(var1: Float): CircleOptions<*>
    abstract fun strokeColor(var1: Int): CircleOptions<*>
    abstract fun strokePattern(var1: List<PatternItem<*>>?): CircleOptions<*>
    abstract fun fillColor(var1: Int): CircleOptions<*>
    abstract fun zIndex(var1: Float): CircleOptions<*>
    abstract fun visible(var1: Boolean): CircleOptions<*>
    abstract fun clickable(var1: Boolean): CircleOptions<*>
    abstract val center: LatLng<*>?
    abstract val radius: Double
    abstract val strokeWidth: Float
    abstract val strokeColor: Int
    abstract val strokePattern: List<PatternItem<*>>?
    abstract val fillColor: Int
    abstract val zIndex: Float
    abstract val isVisible: Boolean
    abstract val isClickable: Boolean
}
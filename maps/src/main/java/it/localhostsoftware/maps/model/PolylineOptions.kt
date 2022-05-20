package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.model.GooglePolylineOptions
import it.localhostsoftware.maps.huawei.model.HuaweiPolylineOptions

abstract class PolylineOptions<PO>(val po: PO) {
    companion object {
        fun getInstance(context: Context): PolylineOptions<*> =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GooglePolylineOptions(com.google.android.gms.maps.model.PolylineOptions())
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiPolylineOptions(com.huawei.hms.maps.model.PolylineOptions())
                else throw IllegalStateException()
    }

    abstract fun add(vararg var1: LatLng<*>): PolylineOptions<*>
    abstract fun width(var1: Float): PolylineOptions<*>
    abstract fun color(var1: Int): PolylineOptions<*>
    abstract fun startCap(var1: Cap<*>): PolylineOptions<*>
    abstract fun endCap(var1: Cap<*>): PolylineOptions<*>
    abstract fun jointType(var1: Int): PolylineOptions<*>
    abstract fun pattern(var1: List<PatternItem<*>>?): PolylineOptions<*>
    abstract fun zIndex(var1: Float): PolylineOptions<*>
    abstract fun visible(var1: Boolean): PolylineOptions<*>
    abstract fun geodesic(var1: Boolean): PolylineOptions<*>
    abstract fun clickable(var1: Boolean): PolylineOptions<*>
    abstract val points: List<LatLng<*>>
    abstract val width: Float
    abstract val color: Int
    abstract val startCap: Cap<*>
    abstract val endCap: Cap<*>
    abstract val jointType: Int
    abstract val pattern: List<PatternItem<*>>?
    abstract val zIndex: Float
    abstract val isVisible: Boolean
    abstract val isGeodesic: Boolean
    abstract val isClickable: Boolean
    fun addAll(var1: Iterable<LatLng<*>>): PolylineOptions<*> {
        add(*var1.toList().toTypedArray())
        return this
    }
}
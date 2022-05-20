package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.model.GooglePolygonOptions
import it.localhostsoftware.maps.huawei.model.HuaweiPolygonOptions

abstract class PolygonOptions<PO>(val po: PO) {
    companion object {
        fun getInstance(context: Context) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GooglePolygonOptions(com.google.android.gms.maps.model.PolygonOptions())
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiPolygonOptions(com.huawei.hms.maps.model.PolygonOptions())
                else throw IllegalStateException()
    }

    abstract fun add(point: LatLng<*>): PolygonOptions<*>
    abstract fun add(vararg points: LatLng<*>): PolygonOptions<*>
    abstract fun addAll(points: Iterable<LatLng<*>>): PolygonOptions<*>
    abstract fun addHole(points: Iterable<LatLng<*>>): PolygonOptions<*>
    abstract fun strokeWidth(width: Float): PolygonOptions<*>
    abstract fun strokeColor(color: Int): PolygonOptions<*>
    abstract fun strokeJointType(jointType: Int): PolygonOptions<*>
    abstract fun strokePattern(pattern: List<PatternItem<*>>?): PolygonOptions<*>
    abstract fun fillColor(color: Int): PolygonOptions<*>
    abstract fun zIndex(zIndex: Float): PolygonOptions<*>
    abstract fun visible(visible: Boolean): PolygonOptions<*>
    abstract fun geodesic(geodesic: Boolean): PolygonOptions<*>
    abstract fun clickable(clickable: Boolean): PolygonOptions<*>
    abstract val points: List<LatLng<*>>
    abstract val holes: List<List<LatLng<*>>>
    abstract val strokeWidth: Float
    abstract val strokeColor: Int
    abstract val strokeJointType: Int
    abstract val strokePattern: List<PatternItem<*>>?
    abstract val fillColor: Int
    abstract val zIndex: Float
    abstract val isVisible: Boolean
    abstract val isGeodesic: Boolean
    abstract val isClickable: Boolean
}
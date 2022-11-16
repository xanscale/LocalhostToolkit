package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GooglePolylineOptions
import it.localhostsoftware.maps.huawei.model.HuaweiPolylineOptions

abstract class PolylineOptions<PO, PI : PatternItem<*>>(val po: PO) {
    companion object {
        fun getInstance(c: Context): PolylineOptions<*, *> =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GooglePolylineOptions(com.google.android.gms.maps.model.PolylineOptions())
                MobileServices.HUAWEI -> HuaweiPolylineOptions(com.huawei.hms.maps.model.PolylineOptions())
                else -> throw IllegalStateException()
            }
    }

    abstract fun add(vararg var1: LatLng<*>): PolylineOptions<*, *>
    abstract val points: List<LatLng<*>>
    abstract var width: Float
    abstract var color: Int
    abstract var startCap: Cap<*>
    abstract var endCap: Cap<*>
    abstract var jointType: Int
    abstract var pattern: List<PI>?
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isGeodesic: Boolean
    abstract var isClickable: Boolean
    fun addAll(var1: Iterable<LatLng<*>>): PolylineOptions<*, *> {
        add(*var1.toList().toTypedArray())
        return this
    }
}
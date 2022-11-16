package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GooglePolygonOptions
import it.localhostsoftware.maps.huawei.model.HuaweiPolygonOptions

abstract class PolygonOptions<PO, PI : PatternItem<*>, LL : LatLng<*>>(val po: PO) {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GooglePolygonOptions(com.google.android.gms.maps.model.PolygonOptions())
                MobileServices.HUAWEI -> HuaweiPolygonOptions(com.huawei.hms.maps.model.PolygonOptions())
                else -> throw IllegalStateException()
            }
    }

    abstract fun add(vararg points: LL): PolygonOptions<*, *, *>
    abstract fun addHole(points: Iterable<LL>): PolygonOptions<*, *, *>
    abstract val points: List<LL>
    abstract val holes: List<List<LL>>
    abstract var strokeWidth: Float
    abstract var strokeColor: Int
    abstract var strokeJointType: Int
    abstract var strokePattern: List<PI>?
    abstract var fillColor: Int
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isGeodesic: Boolean
    abstract var isClickable: Boolean
    fun addAll(var1: Iterable<LL>): PolygonOptions<*, *, *> {
        var1.forEach { add(it) }
        return this
    }
}
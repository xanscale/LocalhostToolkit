package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleCircleOptions
import it.localhostsoftware.maps.huawei.model.HuaweiCircleOptions

abstract class CircleOptions<CO>(val co: CO) {
    companion object {
        fun getInstance(c: Context) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> GoogleCircleOptions(com.google.android.gms.maps.model.CircleOptions())
                    MobileServices.HUAWEI -> HuaweiCircleOptions(com.huawei.hms.maps.model.CircleOptions())
                    else -> throw IllegalStateException()
                }
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
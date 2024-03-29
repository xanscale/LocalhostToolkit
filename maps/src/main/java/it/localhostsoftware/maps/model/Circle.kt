package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.maps.model.CircleOptions
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleCircleOptions
import it.localhostsoftware.maps.huawei.model.HuaweiCircleOptions

abstract class Circle<C, PI : PatternItem<*>, LL : LatLng<*>>(val c: C) {
    abstract val id: String
    abstract var center: LL
    abstract var radius: Double
    abstract var strokeWidth: Float
    abstract var strokeColor: Int
    abstract var strokePattern: List<PI>?
    abstract var fillColor: Int
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isClickable: Boolean
    abstract var tag: Any?
    abstract fun remove()
}

abstract class CircleOptions<CO, LL : LatLng<*>, PI : PatternItem<*>>(val co: CO) {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GoogleCircleOptions(CircleOptions())
                MobileServices.HUAWEI -> HuaweiCircleOptions(com.huawei.hms.maps.model.CircleOptions())
                else -> throw IllegalStateException()
            }
    }

    abstract var center: LL?
    abstract var radius: Double
    abstract var strokeWidth: Float
    abstract var strokeColor: Int
    abstract var strokePattern: List<PI>?
    abstract var fillColor: Int
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isClickable: Boolean
}
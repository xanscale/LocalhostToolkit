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

    abstract var center: LatLng<*>?
    abstract var radius: Double
    abstract var strokeWidth: Float
    abstract var strokeColor: Int
    abstract var strokePattern: List<PatternItem<*>>?
    abstract var fillColor: Int
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isClickable: Boolean
}
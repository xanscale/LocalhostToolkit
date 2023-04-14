package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleLatLng
import it.localhostsoftware.maps.huawei.model.HuaweiLatLng

abstract class LatLng<LL>(val ll: LL) {
    companion object {
        fun getInstance(c: Context, var1: Double, var3: Double) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> GoogleLatLng(com.google.android.gms.maps.model.LatLng(var1, var3))
                    MobileServices.HUAWEI -> HuaweiLatLng(com.huawei.hms.maps.model.LatLng(var1, var3))
                    else -> throw IllegalStateException()
                }
    }

    abstract val latitude: Double
    abstract val longitude: Double
}
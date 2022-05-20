package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices

class CustomCap<C>(c: C) : Cap<C>(c) {
    companion object {
        fun getInstance(c: Context, var1: BitmapDescriptor<*>, var2: Float) = when (c.getMobileServices()) {
            MobileServices.GOOGLE -> CustomCap(com.google.android.gms.maps.model.CustomCap((var1.bitmapDescriptor as com.google.android.gms.maps.model.BitmapDescriptor), var2))
            MobileServices.HUAWEI -> CustomCap(com.huawei.hms.maps.model.CustomCap(var1.bitmapDescriptor as com.huawei.hms.maps.model.BitmapDescriptor, var2))
            else -> throw IllegalStateException()
        }

        fun getInstance(c: Context, var1: BitmapDescriptor<*>) = when (c.getMobileServices()) {
            MobileServices.GOOGLE -> CustomCap(com.google.android.gms.maps.model.CustomCap((var1.bitmapDescriptor as com.google.android.gms.maps.model.BitmapDescriptor)))
            MobileServices.HUAWEI -> CustomCap(com.huawei.hms.maps.model.CustomCap(var1.bitmapDescriptor as com.huawei.hms.maps.model.BitmapDescriptor))
            else -> throw IllegalStateException()
        }
    }
}
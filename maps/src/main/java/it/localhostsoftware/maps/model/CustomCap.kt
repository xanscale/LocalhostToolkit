package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

class CustomCap<C>(c: C) : Cap<C>(c) {
    companion object {
        fun getInstance(context: Context, var1: BitmapDescriptor<*>, var2: Float) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) CustomCap(com.google.android.gms.maps.model.CustomCap((var1.bitmapDescriptor as com.google.android.gms.maps.model.BitmapDescriptor), var2))
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) CustomCap(com.huawei.hms.maps.model.CustomCap(var1.bitmapDescriptor as com.huawei.hms.maps.model.BitmapDescriptor, var2))
                else throw IllegalStateException()

        fun getInstance(context: Context, var1: BitmapDescriptor<*>) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) CustomCap(com.google.android.gms.maps.model.CustomCap((var1.bitmapDescriptor as com.google.android.gms.maps.model.BitmapDescriptor)))
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) CustomCap(com.huawei.hms.maps.model.CustomCap(var1.bitmapDescriptor as com.huawei.hms.maps.model.BitmapDescriptor))
                else throw IllegalStateException()
    }
}
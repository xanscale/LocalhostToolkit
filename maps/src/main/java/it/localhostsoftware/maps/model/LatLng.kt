package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.model.GoogleLatLng
import it.localhostsoftware.maps.huawei.model.HuaweiLatLng

abstract class LatLng<LL>(val ll: LL) {
    companion object {
        fun getInstance(context: Context, var1: Double, var3: Double) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GoogleLatLng(com.google.android.gms.maps.model.LatLng(var1, var3))
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiLatLng(com.huawei.hms.maps.model.LatLng(var1, var3))
                else throw IllegalStateException()
    }

    abstract val latitude: Double
    abstract val longitude: Double
}
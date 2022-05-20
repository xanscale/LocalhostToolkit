package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

class PatternItem<PI>(val pi: PI) {
    companion object {
        fun getInstance(context: Context, var1: Int, var2: Float) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) PatternItem(com.google.android.gms.maps.model.PatternItem(var1, var2))
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) PatternItem(com.huawei.hms.maps.model.PatternItem(var1, var2))
                else throw IllegalStateException()
    }
}
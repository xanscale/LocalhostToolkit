package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

class SquareCap<C>(c: C) : Cap<C>(c) {
    companion object {
        fun getInstance(context: Context) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) SquareCap(com.google.android.gms.maps.model.SquareCap())
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) SquareCap(com.huawei.hms.maps.model.SquareCap())
                else throw IllegalStateException()
    }
}
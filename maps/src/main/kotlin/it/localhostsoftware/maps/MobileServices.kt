package it.localhostsoftware.maps

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

enum class MobileServices { GOOGLE, HUAWEI, NONE }

fun Context.getMobileServices() =
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) == com.google.android.gms.common.ConnectionResult.SUCCESS) MobileServices.GOOGLE
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this) == com.huawei.hms.api.ConnectionResult.SUCCESS) MobileServices.HUAWEI
        else MobileServices.NONE
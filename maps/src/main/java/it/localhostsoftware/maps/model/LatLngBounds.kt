package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.model.GoogleLatLngBounds.GoogleBuilder
import it.localhostsoftware.maps.huawei.model.HuaweiLatLngBounds.HuaweiBuilder

abstract class LatLngBounds<LB>(val lb: LB) {
    abstract val southwest: LatLng<*>
    abstract val northeast: LatLng<*>
    abstract operator fun contains(var1: LatLng<*>): Boolean
    abstract fun including(var1: LatLng<*>): LatLngBounds<*>
    abstract val center: LatLng<*>

    abstract class Builder<B>(val builder: B) {
        companion object {
            fun getInstance(context: Context): Builder<*> =
                    if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GoogleBuilder(com.google.android.gms.maps.model.LatLngBounds.Builder())
                    else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiBuilder(com.huawei.hms.maps.model.LatLngBounds.Builder())
                    else throw IllegalStateException()
        }

        abstract fun include(var1: LatLng<*>): Builder<*>
        abstract fun build(): LatLngBounds<*>
    }
}
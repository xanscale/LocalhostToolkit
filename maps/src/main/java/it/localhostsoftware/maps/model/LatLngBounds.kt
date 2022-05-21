package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleLatLngBounds.GoogleBuilder
import it.localhostsoftware.maps.huawei.model.HuaweiLatLngBounds.HuaweiBuilder

abstract class LatLngBounds<LB>(val lb: LB) {
    abstract val southwest: LatLng<*>
    abstract val northeast: LatLng<*>
    abstract val center: LatLng<*>
    abstract operator fun contains(var1: LatLng<*>): Boolean
    abstract fun including(var1: LatLng<*>): LatLngBounds<*>
    abstract class Builder<B>(val builder: B) {
        companion object {
            fun getInstance(c: Context): Builder<*> =
                    when (c.getMobileServices()) {
                        MobileServices.GOOGLE -> GoogleBuilder(com.google.android.gms.maps.model.LatLngBounds.Builder())
                        MobileServices.HUAWEI -> HuaweiBuilder(com.huawei.hms.maps.model.LatLngBounds.Builder())
                        else -> throw IllegalStateException()
                    }
        }

        abstract fun include(var1: LatLng<*>): Builder<*>
        abstract fun build(): LatLngBounds<*>
    }
}
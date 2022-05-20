package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.LatLngBounds
import it.localhostsoftware.maps.model.LatLng

class GoogleLatLngBounds(latLngBounds: LatLngBounds) : it.localhostsoftware.maps.model.LatLngBounds<LatLngBounds>(latLngBounds) {
    override val southwest: LatLng<*>
        get() = GoogleLatLng(lb.southwest)
    override val northeast: LatLng<*>
        get() = GoogleLatLng(lb.northeast)

    override fun contains(var1: LatLng<*>): Boolean {
        return lb.contains(var1.ll as com.google.android.gms.maps.model.LatLng)
    }

    override fun including(var1: LatLng<*>): it.localhostsoftware.maps.model.LatLngBounds<*> {
        lb.including(var1.ll as com.google.android.gms.maps.model.LatLng)
        return this
    }

    override val center: LatLng<*>
        get() = GoogleLatLng(lb.center)

    class GoogleBuilder(builder: LatLngBounds.Builder) : Builder<LatLngBounds.Builder>(builder) {
        override fun include(var1: LatLng<*>): Builder<*> {
            builder.include(var1.ll as com.google.android.gms.maps.model.LatLng)
            return this
        }

        override fun build(): it.localhostsoftware.maps.model.LatLngBounds<*> {
            return GoogleLatLngBounds(builder.build())
        }
    }
}
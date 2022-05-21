package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.LatLngBounds
import it.localhostsoftware.maps.model.LatLng

class GoogleLatLngBounds(lb: LatLngBounds) : it.localhostsoftware.maps.model.LatLngBounds<LatLngBounds>(lb) {
    override val southwest: LatLng<*>
        get() = GoogleLatLng(lb.southwest)
    override val northeast: LatLng<*>
        get() = GoogleLatLng(lb.northeast)
    override val center: LatLng<*>
        get() = GoogleLatLng(lb.center)

    override fun contains(var1: LatLng<*>) =
            lb.contains(var1.ll as com.google.android.gms.maps.model.LatLng)

    override fun including(var1: LatLng<*>): it.localhostsoftware.maps.model.LatLngBounds<*> {
        lb.including(var1.ll as com.google.android.gms.maps.model.LatLng)
        return this
    }

    class GoogleBuilder(builder: LatLngBounds.Builder) : Builder<LatLngBounds.Builder>(builder) {
        override fun include(var1: LatLng<*>): Builder<*> {
            builder.include(var1.ll as com.google.android.gms.maps.model.LatLng)
            return this
        }

        override fun build() =
                GoogleLatLngBounds(builder.build())
    }
}
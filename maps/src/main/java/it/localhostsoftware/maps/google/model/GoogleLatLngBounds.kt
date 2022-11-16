package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.LatLngBounds

class GoogleLatLngBounds(lb: LatLngBounds) : it.localhostsoftware.maps.model.LatLngBounds<LatLngBounds, GoogleLatLng>(lb) {
    override val southwest: GoogleLatLng
        get() = GoogleLatLng(lb.southwest)
    override val northeast: GoogleLatLng
        get() = GoogleLatLng(lb.northeast)
    override val center: GoogleLatLng
        get() = GoogleLatLng(lb.center)

    override fun contains(var1: GoogleLatLng) =
        lb.contains(var1.ll)

    override fun including(var1: GoogleLatLng): it.localhostsoftware.maps.model.LatLngBounds<*, *> {
        lb.including(var1.ll)
        return this
    }

    class GoogleBuilder(builder: LatLngBounds.Builder) : Builder<LatLngBounds.Builder, GoogleLatLng>(builder) {
        override fun include(var1: GoogleLatLng): Builder<*, *> {
            builder.include(var1.ll)
            return this
        }

        override fun build() =
            GoogleLatLngBounds(builder.build())
    }
}
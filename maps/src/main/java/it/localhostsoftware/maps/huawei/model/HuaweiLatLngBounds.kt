package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.LatLngBounds

class HuaweiLatLngBounds(lb: LatLngBounds) : it.localhostsoftware.maps.model.LatLngBounds<LatLngBounds, HuaweiLatLng>(lb) {
    override val southwest: HuaweiLatLng
        get() = HuaweiLatLng(lb.southwest)
    override val northeast: HuaweiLatLng
        get() = HuaweiLatLng(lb.northeast)
    override val center: HuaweiLatLng
        get() = HuaweiLatLng(lb.center)

    override fun contains(var1: HuaweiLatLng) =
        lb.contains(var1.ll)

    override fun including(var1: HuaweiLatLng): it.localhostsoftware.maps.model.LatLngBounds<*, *> {
        lb.including(var1.ll)
        return this
    }

    class HuaweiBuilder(builder: LatLngBounds.Builder) : Builder<LatLngBounds.Builder, HuaweiLatLng>(builder) {
        override fun include(var1: HuaweiLatLng): Builder<*, *> {
            builder.include(var1.ll)
            return this
        }

        override fun build() =
            HuaweiLatLngBounds(builder.build())
    }
}
package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.LatLngBounds
import it.localhostsoftware.maps.model.LatLng

class HuaweiLatLngBounds(lb: LatLngBounds) : it.localhostsoftware.maps.model.LatLngBounds<LatLngBounds>(lb) {
    override val southwest: LatLng<*>
        get() = HuaweiLatLng(lb.southwest)
    override val northeast: LatLng<*>
        get() = HuaweiLatLng(lb.northeast)

    override fun contains(var1: LatLng<*>): Boolean = lb.contains(var1.ll as com.huawei.hms.maps.model.LatLng)
    override fun including(var1: LatLng<*>): it.localhostsoftware.maps.model.LatLngBounds<*> {
        lb.including(var1.ll as com.huawei.hms.maps.model.LatLng)
        return this
    }

    override val center: LatLng<*>
        get() = HuaweiLatLng(lb.center)

    class HuaweiBuilder(builder: LatLngBounds.Builder) : Builder<LatLngBounds.Builder>(builder) {
        override fun include(var1: LatLng<*>): Builder<*> {
            builder.include(var1.ll as com.huawei.hms.maps.model.LatLng)
            return this
        }

        override fun build() = HuaweiLatLngBounds(builder.build())
    }
}
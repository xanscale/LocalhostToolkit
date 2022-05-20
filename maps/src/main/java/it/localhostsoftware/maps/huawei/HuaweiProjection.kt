package it.localhostsoftware.maps.huawei

import android.graphics.Point
import com.huawei.hms.maps.Projection
import it.localhostsoftware.maps.huawei.model.HuaweiLatLng
import it.localhostsoftware.maps.huawei.model.HuaweiVisibleRegion
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.VisibleRegion

class HuaweiProjection(projection: Projection) : it.localhostsoftware.maps.Projection<Projection>(projection) {
    override fun fromScreenLocation(var1: Point): LatLng<*> {
        return HuaweiLatLng(p.fromScreenLocation(var1))
    }

    override fun toScreenLocation(var1: LatLng<*>): Point {
        return p.toScreenLocation(var1.ll as com.huawei.hms.maps.model.LatLng)
    }

    override val visibleRegion: VisibleRegion<*>
        get() = HuaweiVisibleRegion(p.visibleRegion)
}
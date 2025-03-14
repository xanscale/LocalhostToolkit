package it.localhostsoftware.maps.huawei

import android.graphics.Point
import com.huawei.hms.maps.Projection
import it.localhostsoftware.maps.huawei.model.HuaweiLatLng
import it.localhostsoftware.maps.huawei.model.HuaweiVisibleRegion
import it.localhostsoftware.maps.model.VisibleRegion

class HuaweiProjection(projection: Projection) : it.localhostsoftware.maps.Projection<Projection, HuaweiLatLng>(projection) {
    override fun fromScreenLocation(var1: Point) =
        HuaweiLatLng(p.fromScreenLocation(var1))

    override fun toScreenLocation(var1: HuaweiLatLng): Point? =
        p.toScreenLocation(var1.ll)

    override val visibleRegion: VisibleRegion<*, *, *>
        get() = HuaweiVisibleRegion(p.visibleRegion)
}
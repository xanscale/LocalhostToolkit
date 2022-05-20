package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.PolylineOptions
import it.localhostsoftware.maps.model.Cap
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.PatternItem

class HuaweiPolylineOptions(polylineOptions: PolylineOptions) : it.localhostsoftware.maps.model.PolylineOptions<PolylineOptions>(polylineOptions) {
    override fun add(vararg var1: LatLng<*>): it.localhostsoftware.maps.model.PolylineOptions<*> {
        var1.forEach { po.add(it.ll as com.huawei.hms.maps.model.LatLng) }
        return this
    }

    override fun width(var1: Float): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.width(var1)
        return this
    }

    override fun color(var1: Int): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.color(var1)
        return this
    }

    override fun startCap(var1: Cap<*>): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.startCap(var1.cap as com.huawei.hms.maps.model.Cap)
        return this
    }

    override fun endCap(var1: Cap<*>): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.endCap(var1.cap as com.huawei.hms.maps.model.Cap)
        return this
    }

    override fun jointType(var1: Int): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.jointType(var1)
        return this
    }

    override fun pattern(var1: List<PatternItem<*>>?): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.pattern(var1?.map { it.pi as com.huawei.hms.maps.model.PatternItem })
        return this
    }

    override fun zIndex(var1: Float): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.zIndex(var1)
        return this
    }

    override fun visible(var1: Boolean): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.visible(var1)
        return this
    }

    override fun geodesic(var1: Boolean): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.geodesic(var1)
        return this
    }

    override fun clickable(var1: Boolean): it.localhostsoftware.maps.model.PolylineOptions<*> {
        po.clickable(var1)
        return this
    }

    override val points: List<LatLng<*>>
        get() = po.points.map { HuaweiLatLng(it) }
    override val width: Float
        get() = po.width
    override val color: Int
        get() = po.color
    override val startCap: Cap<*>
        get() = Cap(po.startCap)
    override val endCap: Cap<*>
        get() = Cap(po.endCap)
    override val jointType: Int
        get() = po.jointType
    override val pattern: List<PatternItem<*>>?
        get() = po.pattern?.map { PatternItem(it) }
    override val zIndex: Float
        get() = po.zIndex
    override val isVisible: Boolean
        get() = po.isVisible
    override val isGeodesic: Boolean
        get() = po.isGeodesic
    override val isClickable: Boolean
        get() = po.isClickable
}
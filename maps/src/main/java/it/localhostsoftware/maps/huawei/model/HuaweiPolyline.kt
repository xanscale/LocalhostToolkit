package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.Polyline
import com.huawei.hms.maps.model.PolylineOptions

class HuaweiPolyline(polyline: Polyline) : it.localhostsoftware.maps.model.Polyline<Polyline, HuaweiPatternItem, HuaweiLatLng, HuaweiCap>(polyline) {
    override fun remove() {
        p.remove()
    }

    override val id: String
        get() = p.id
    override var points: List<HuaweiLatLng>
        get() = p.points.map { HuaweiLatLng(it) }
        set(var1) {
            p.points = var1.map { it.ll }
        }
    override var width: Float
        get() = p.width
        set(var1) {
            p.width = var1
        }
    override var color: Int
        get() = p.color
        set(var1) {
            p.color = var1
        }
    override var startCap: HuaweiCap
        get() = HuaweiCap(p.startCap)
        set(var1) {
            p.startCap = var1.cap
        }
    override var endCap: HuaweiCap
        get() = HuaweiCap(p.endCap)
        set(var1) {
            p.endCap = var1.cap
        }
    override var jointType: Int
        get() = p.jointType
        set(var1) {
            p.jointType = var1
        }
    override var pattern: List<HuaweiPatternItem>?
        get() = p.pattern?.map { HuaweiPatternItem(it) }
        set(var1) {
            p.pattern = var1?.map { it.pi }
        }
    override var zIndex: Float
        get() = p.zIndex
        set(var1) {
            p.zIndex = var1
        }
    override var isVisible: Boolean
        get() = p.isVisible
        set(var1) {
            p.isVisible = var1
        }
    override var isGeodesic: Boolean
        get() = p.isGeodesic
        set(var1) {
            p.isGeodesic = var1
        }
    override var isClickable: Boolean
        get() = p.isClickable
        set(var1) {
            p.isClickable = var1
        }
    override var tag: Any?
        get() = p.tag
        set(var1) {
            p.tag = var1
        }

    override fun equals(other: Any?): Boolean = p == other
    override fun hashCode(): Int = p.hashCode()
}

class HuaweiPolylineOptions(polylineOptions: PolylineOptions) : it.localhostsoftware.maps.model.PolylineOptions<PolylineOptions, HuaweiPatternItem, HuaweiLatLng, HuaweiCap>(polylineOptions) {
    override fun add(vararg var1: HuaweiLatLng): it.localhostsoftware.maps.model.PolylineOptions<PolylineOptions, HuaweiPatternItem, HuaweiLatLng, HuaweiCap> {
        po.add(*var1.map { it.ll }.toTypedArray())
        return this
    }

    override val points: List<HuaweiLatLng>
        get() = po.points.map { HuaweiLatLng(it) }
    override var width: Float
        get() = po.width
        set(value) {
            po.width(value)
        }
    override var color: Int
        get() = po.color
        set(value) {
            po.color(value)
        }
    override var startCap: HuaweiCap
        get() = HuaweiCap(po.startCap)
        set(value) {
            po.startCap(value.cap)
        }
    override var endCap: HuaweiCap
        get() = HuaweiCap(po.endCap)
        set(value) {
            po.endCap(value.cap)
        }
    override var jointType: Int
        get() = po.jointType
        set(value) {
            po.jointType(value)
        }
    override var pattern: List<HuaweiPatternItem>?
        get() = po.pattern?.map { HuaweiPatternItem(it) }
        set(value) {
            po.pattern(value?.map { it.pi })
        }
    override var zIndex: Float
        get() = po.zIndex
        set(value) {
            po.zIndex(value)
        }
    override var isVisible: Boolean
        get() = po.isVisible
        set(value) {
            po.visible(value)
        }
    override var isGeodesic: Boolean
        get() = po.isGeodesic
        set(value) {
            po.geodesic(value)
        }
    override var isClickable: Boolean
        get() = po.isClickable
        set(value) {
            po.clickable(value)
        }
}
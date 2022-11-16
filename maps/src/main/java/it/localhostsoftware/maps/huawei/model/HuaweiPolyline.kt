package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.Polyline
import it.localhostsoftware.maps.model.Cap
import it.localhostsoftware.maps.model.LatLng

class HuaweiPolyline(polyline: Polyline) : it.localhostsoftware.maps.model.Polyline<Polyline, HuaweiPatternItem>(polyline) {
    override fun remove() {
        p.remove()
    }

    override val id: String
        get() = p.id
    override var points: List<LatLng<*>>
        get() = p.points.map { HuaweiLatLng(it) }
        set(var1) {
            p.points = var1.map { it.ll as com.huawei.hms.maps.model.LatLng }
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
    override var startCap: Cap<*>
        get() = Cap(p.startCap)
        set(var1) {
            p.startCap = var1.cap as com.huawei.hms.maps.model.Cap
        }
    override var endCap: Cap<*>
        get() = Cap(p.endCap)
        set(var1) {
            p.endCap = var1.cap as com.huawei.hms.maps.model.Cap
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
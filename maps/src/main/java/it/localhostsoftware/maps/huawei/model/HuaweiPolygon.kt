package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.Polygon
import it.localhostsoftware.maps.model.LatLng

class HuaweiPolygon(polygon: Polygon) : it.localhostsoftware.maps.model.Polygon<Polygon, HuaweiPatternItem>(polygon) {
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
    override var holes: List<List<LatLng<*>>>
        get() = p.holes.map { it.map { ll -> HuaweiLatLng(ll) } }
        set(var1) {
            p.holes = var1.map { it.map { ll -> ll.ll as com.huawei.hms.maps.model.LatLng } }
        }
    override var strokeWidth: Float
        get() = p.strokeWidth
        set(var1) {
            p.strokeWidth = var1
        }
    override var strokeColor: Int
        get() = p.strokeColor
        set(var1) {
            p.strokeWidth = var1.toFloat()
        }
    override var strokeJointType: Int
        get() = p.strokeJointType
        set(var1) {
            p.strokeJointType = var1
        }
    override var strokePattern: List<HuaweiPatternItem>?
        get() =
            p.strokePattern?.map { HuaweiPatternItem(it) }
        set(var1) {
            p.strokePattern = var1?.map { it.pi }
        }
    override var fillColor: Int
        get() = p.fillColor
        set(var1) {
            p.fillColor = var1
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
}
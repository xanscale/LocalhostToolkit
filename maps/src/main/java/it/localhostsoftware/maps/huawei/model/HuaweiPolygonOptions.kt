package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.PolygonOptions
import it.localhostsoftware.maps.model.LatLng

class HuaweiPolygonOptions(polygonOptions: PolygonOptions) : it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions, HuaweiPatternItem>(polygonOptions) {
    override fun add(vararg points: LatLng<*>): it.localhostsoftware.maps.model.PolygonOptions<*, *> {
        po.add(*points.map { it.ll as com.huawei.hms.maps.model.LatLng }.toTypedArray())
        return this
    }

    override fun addHole(points: Iterable<LatLng<*>>): it.localhostsoftware.maps.model.PolygonOptions<*, *> {
        po.addHole(points.map { it.ll as com.huawei.hms.maps.model.LatLng })
        return this
    }

    override val points: List<LatLng<*>>
        get() = po.points.map { HuaweiLatLng(it) }
    override val holes: List<List<LatLng<*>>>
        get() = po.holes.map { it.map { ll -> HuaweiLatLng(ll) } }
    override var strokeWidth: Float
        get() = po.strokeWidth
        set(value) {
            po.strokeWidth(value)
        }
    override var strokeColor: Int
        get() = po.strokeColor
        set(value) {
            po.strokeColor(value)
        }
    override var strokeJointType: Int
        get() = po.strokeJointType
        set(value) {
            po.strokeJointType(value)
        }
    override var strokePattern: List<HuaweiPatternItem>?
        get() = po.strokePattern?.map { HuaweiPatternItem(it) }
        set(value) {
            po.strokePattern(value?.map { it.pi })
        }
    override var fillColor: Int
        get() = po.fillColor
        set(value) {
            po.fillColor(value)
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
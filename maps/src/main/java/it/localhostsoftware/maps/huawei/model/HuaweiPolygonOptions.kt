package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.PolygonOptions

class HuaweiPolygonOptions(polygonOptions: PolygonOptions) : it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions, HuaweiPatternItem, HuaweiLatLng>(polygonOptions) {
    override fun add(vararg points: HuaweiLatLng): it.localhostsoftware.maps.model.PolygonOptions<*, *, *> {
        po.add(*points.map { it.ll }.toTypedArray())
        return this
    }

    override fun addHole(points: Iterable<HuaweiLatLng>): it.localhostsoftware.maps.model.PolygonOptions<*, *, *> {
        po.addHole(points.map { it.ll })
        return this
    }

    override val points: List<HuaweiLatLng>
        get() = po.points.map { HuaweiLatLng(it) }
    override val holes: List<List<HuaweiLatLng>>
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
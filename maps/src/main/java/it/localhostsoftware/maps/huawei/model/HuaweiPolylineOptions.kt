package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.PolylineOptions

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
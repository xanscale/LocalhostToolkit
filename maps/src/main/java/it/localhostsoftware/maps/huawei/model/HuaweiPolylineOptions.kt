package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.PolylineOptions
import it.localhostsoftware.maps.model.Cap
import it.localhostsoftware.maps.model.LatLng

class HuaweiPolylineOptions(polylineOptions: PolylineOptions) : it.localhostsoftware.maps.model.PolylineOptions<PolylineOptions, HuaweiPatternItem>(polylineOptions) {
    override fun add(vararg var1: LatLng<*>): it.localhostsoftware.maps.model.PolylineOptions<*, *> {
        po.add(*var1.map { it.ll as com.huawei.hms.maps.model.LatLng }.toTypedArray())
        return this
    }

    override val points: List<LatLng<*>>
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
    override var startCap: Cap<*>
        get() = Cap(po.startCap)
        set(value) {
            po.startCap(value.cap as com.huawei.hms.maps.model.Cap)
        }
    override var endCap: Cap<*>
        get() = Cap(po.endCap)
        set(value) {
            po.endCap(value.cap as com.huawei.hms.maps.model.Cap)
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
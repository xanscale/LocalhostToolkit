package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.MarkerOptions
import it.localhostsoftware.maps.model.BitmapDescriptor
import it.localhostsoftware.maps.model.LatLng

class HuaweiMarkerOptions(markerOptions: MarkerOptions) : it.localhostsoftware.maps.model.MarkerOptions<MarkerOptions>(markerOptions) {
    override var position: LatLng<*>
        get() = HuaweiLatLng(mo.position)
        set(value) {
            mo.position(value.ll as com.huawei.hms.maps.model.LatLng)
        }
    override var title: String?
        get() = mo.title
        set(value) {
            mo.title(value)
        }
    override var snippet: String?
        get() = mo.snippet
        set(value) {
            mo.snippet(value)
        }
    override var icon: BitmapDescriptor<*>?
        get() = BitmapDescriptor(mo.icon)
        set(value) {
            mo.icon(value?.bitmapDescriptor as? com.huawei.hms.maps.model.BitmapDescriptor)
        }
    override var isDraggable: Boolean
        get() = mo.isDraggable
        set(value) {
            mo.draggable(value)
        }
    override var isVisible: Boolean
        get() = mo.isVisible
        set(value) {
            mo.visible(value)
        }
    override var isFlat: Boolean
        get() = mo.isFlat
        set(value) {
            mo.flat(value)
        }
    override var rotation: Float
        get() = mo.rotation
        set(value) {
            mo.rotation(value)
        }
    override var alpha: Float
        get() = mo.alpha
        set(value) {
            mo.alpha(value)
        }
    override var zIndex: Float
        get() = mo.zIndex
        set(value) {
            mo.zIndex(value)
        }
    override var anchor: Pair<Float, Float>
        get() = Pair(mo.markerAnchorU, mo.markerAnchorV)
        set(value) {
            mo.anchorMarker(value.first, value.second)
        }
    override var infoWindowAnchor: Pair<Float, Float>
        get() = Pair(mo.infoWindowAnchorU, mo.infoWindowAnchorV)
        set(value) {
            mo.infoWindowAnchor(value.first, value.second)
        }
}
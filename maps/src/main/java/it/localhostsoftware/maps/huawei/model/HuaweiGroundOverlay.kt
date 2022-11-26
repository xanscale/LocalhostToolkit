package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.GroundOverlay

class HuaweiGroundOverlay(groundOverlay: GroundOverlay) : it.localhostsoftware.maps.model.GroundOverlay<GroundOverlay, HuaweiBitmapDescriptor, HuaweiLatLng, HuaweiLatLngBounds>(groundOverlay) {
    override var bearing: Float
        get() = go.bearing
        set(value) {
            go.bearing = value
        }
    override val height: Float
        get() = go.height
    override val width: Float
        get() = go.width
    override var transparency: Float
        get() = go.transparency
        set(value) {
            go.transparency = value
        }
    override var zIndex: Float
        get() = go.zIndex
        set(value) {
            go.zIndex = value
        }
    override var position: HuaweiLatLng
        get() = HuaweiLatLng(go.position)
        set(value) {
            go.position = value.ll
        }
    override var bounds: HuaweiLatLngBounds
        get() = HuaweiLatLngBounds(go.bounds)
        set(value) {
            go.setPositionFromBounds(value.lb)
        }
    override var tag: Any?
        get() = go.tag
        set(value) {
            go.tag = value
        }
    override var clickable: Boolean
        get() = go.isClickable
        set(value) {
            go.isClickable = value
        }
    override var visible: Boolean
        get() = go.isVisible
        set(value) {
            go.isVisible = value
        }
    override val id: String
        get() = go.id

    override fun remove() =
        go.remove()

    override fun setDimensions(param1: Float) =
        go.setDimensions(param1)

    override fun setDimensions(param1: Float, param2: Float) =
        go.setDimensions(param1, param2)

    override fun setImage(param1: HuaweiBitmapDescriptor) =
        go.setImage(param1.bitmapDescriptor)
}
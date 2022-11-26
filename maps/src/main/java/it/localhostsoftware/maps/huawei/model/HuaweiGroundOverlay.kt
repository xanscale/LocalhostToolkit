package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.GroundOverlay
import com.huawei.hms.maps.model.GroundOverlayOptions

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

class HuaweiGroundOverlayOptions(groundOverlayOptions: GroundOverlayOptions) : it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, HuaweiBitmapDescriptor, HuaweiLatLng, HuaweiLatLngBounds>(groundOverlayOptions) {
    override var anchor: Pair<Float, Float>
        get() = Pair(goo.anchorU, goo.anchorV)
        set(value) {
            goo.anchor(value.first, value.second)
        }
    override var bearing: Float
        get() = goo.bearing
        set(value) {
            goo.bearing(value)
        }
    override var zIndex: Float
        get() = goo.zIndex
        set(value) {
            goo.zIndex(value)
        }
    override var clickable: Boolean
        get() = goo.isClickable
        set(value) {
            goo.clickable(value)
        }
    override var image: HuaweiBitmapDescriptor
        get() = HuaweiBitmapDescriptor(goo.image)
        set(value) {
            goo.image(value.bitmapDescriptor)
        }
    override var transparency: Float
        get() = goo.transparency
        set(value) {
            goo.transparency(value)
        }
    override var visible: Boolean
        get() = goo.isVisible
        set(value) {
            goo.visible(value)
        }
    override val height: Float
        get() = goo.height
    override val width: Float
        get() = goo.width
    override val location: HuaweiLatLng?
        get() = goo.location?.let { HuaweiLatLng(it) }
    override val bounds: HuaweiLatLngBounds?
        get() = goo.bounds?.let { HuaweiLatLngBounds(it) }

    override fun position(location: HuaweiLatLng, width: Float): it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, HuaweiBitmapDescriptor, HuaweiLatLng, HuaweiLatLngBounds> {
        goo.position(location.ll, width)
        return this
    }

    override fun position(location: HuaweiLatLng, width: Float, height: Float): it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, HuaweiBitmapDescriptor, HuaweiLatLng, HuaweiLatLngBounds> {
        goo.position(location.ll, width, height)
        return this
    }

    override fun positionFromBounds(bounds: HuaweiLatLngBounds): it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, HuaweiBitmapDescriptor, HuaweiLatLng, HuaweiLatLngBounds> {
        goo.positionFromBounds(bounds.lb)
        return this
    }
}
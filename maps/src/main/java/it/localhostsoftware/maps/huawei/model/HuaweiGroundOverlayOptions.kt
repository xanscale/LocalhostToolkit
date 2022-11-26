package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.GroundOverlayOptions

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
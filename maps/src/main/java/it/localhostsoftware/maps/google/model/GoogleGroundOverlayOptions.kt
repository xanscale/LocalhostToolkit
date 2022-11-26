package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.GroundOverlayOptions

class GoogleGroundOverlayOptions(groundOverlayOptions: GroundOverlayOptions) : it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, GoogleBitmapDescriptor, GoogleLatLng, GoogleLatLngBounds>(groundOverlayOptions) {
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
    override var image: GoogleBitmapDescriptor
        get() = GoogleBitmapDescriptor(goo.image)
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
    override val location: GoogleLatLng?
        get() = goo.location?.let { GoogleLatLng(it) }
    override val bounds: GoogleLatLngBounds?
        get() = goo.bounds?.let { GoogleLatLngBounds(it) }

    override fun position(location: GoogleLatLng, width: Float): it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, GoogleBitmapDescriptor, GoogleLatLng, GoogleLatLngBounds> {
        goo.position(location.ll, width)
        return this
    }

    override fun position(location: GoogleLatLng, width: Float, height: Float): it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, GoogleBitmapDescriptor, GoogleLatLng, GoogleLatLngBounds> {
        goo.position(location.ll, width, height)
        return this
    }

    override fun positionFromBounds(bounds: GoogleLatLngBounds): it.localhostsoftware.maps.model.GroundOverlayOptions<GroundOverlayOptions, GoogleBitmapDescriptor, GoogleLatLng, GoogleLatLngBounds> {
        goo.positionFromBounds(bounds.lb)
        return this
    }
}
package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.MarkerOptions
import it.localhostsoftware.maps.model.BitmapDescriptor
import it.localhostsoftware.maps.model.LatLng

class GoogleMarkerOptions(markerOptions: MarkerOptions) : it.localhostsoftware.maps.model.MarkerOptions<MarkerOptions>(markerOptions) {
    override fun position(var1: LatLng<*>): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.position(var1.ll as com.google.android.gms.maps.model.LatLng)
        return this
    }

    override fun zIndex(var1: Float): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.zIndex(var1)
        return this
    }

    override fun icon(var1: BitmapDescriptor<*>?): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.icon(var1?.bitmapDescriptor as? com.google.android.gms.maps.model.BitmapDescriptor)
        return this
    }

    override fun anchor(var1: Float, var2: Float): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.anchor(var1, var2)
        return this
    }

    override fun infoWindowAnchor(var1: Float, var2: Float): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.infoWindowAnchor(var1, var2)
        return this
    }

    override fun title(var1: String): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.title(var1)
        return this
    }

    override fun snippet(var1: String): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.snippet(var1)
        return this
    }

    override fun draggable(var1: Boolean): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.draggable(var1)
        return this
    }

    override fun visible(var1: Boolean): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.visible(var1)
        return this
    }

    override fun flat(var1: Boolean): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.flat(var1)
        return this
    }

    override fun rotation(var1: Float): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.rotation(var1)
        return this
    }

    override fun alpha(var1: Float): it.localhostsoftware.maps.model.MarkerOptions<*> {
        mo.alpha(var1)
        return this
    }

    override val position: LatLng<*>
        get() = GoogleLatLng(mo.position)
    override val title: String?
        get() = mo.title
    override val snippet: String?
        get() = mo.snippet
    override val icon: BitmapDescriptor<*>
        get() = BitmapDescriptor(mo.icon)
    override val anchorU: Float
        get() = mo.anchorU
    override val anchorV: Float
        get() = mo.anchorV
    override val isDraggable: Boolean
        get() = mo.isDraggable
    override val isVisible: Boolean
        get() = mo.isVisible
    override val isFlat: Boolean
        get() = mo.isFlat
    override val rotation: Float
        get() = mo.rotation
    override val infoWindowAnchorU: Float
        get() = mo.infoWindowAnchorU
    override val infoWindowAnchorV: Float
        get() = mo.infoWindowAnchorV
    override val alpha: Float
        get() = mo.alpha
    override val zIndex: Float
        get() = mo.zIndex
}
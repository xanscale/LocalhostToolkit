package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.Marker
import com.huawei.hms.maps.model.MarkerOptions

class HuaweiMarker(marker: Marker) : it.localhostsoftware.maps.model.Marker<Marker, HuaweiLatLng, HuaweiBitmapDescriptor>(marker) {
    override fun remove() {
        m.remove()
    }

    override val id: String
        get() = m.id
    override var position: HuaweiLatLng
        get() = HuaweiLatLng(m.position)
        set(value) {
            m.position = value.ll
        }
    override var zIndex: Float
        get() = m.zIndex
        set(v) {
            m.zIndex = v
        }

    override fun setIcon(var1: HuaweiBitmapDescriptor?) {
        m.setIcon(var1?.bitmapDescriptor)
    }

    override fun setAnchor(var1: Float, var2: Float) {
        m.setMarkerAnchor(var1, var2)
    }

    override fun setInfoWindowAnchor(var1: Float, var2: Float) {
        m.setInfoWindowAnchor(var1, var2)
    }

    override var title: String?
        get() = m.title
        set(s) {
            m.title = s
        }
    override var snippet: String?
        get() = m.snippet
        set(s) {
            m.snippet = s
        }
    override var isDraggable: Boolean
        get() = m.isDraggable
        set(b) {
            m.isDraggable = b
        }

    override fun showInfoWindow() {
        m.showInfoWindow()
    }

    override fun hideInfoWindow() {
        m.hideInfoWindow()
    }

    override val isInfoWindowShown: Boolean
        get() = m.isInfoWindowShown
    override var isVisible: Boolean
        get() = m.isVisible
        set(b) {
            m.isVisible = b
        }
    override var isFlat: Boolean
        get() = m.isFlat
        set(b) {
            m.isFlat = b
        }
    override var rotation: Float
        get() = m.rotation
        set(v) {
            m.rotation = v
        }
    override var alpha: Float
        get() = m.alpha
        set(v) {
            m.alpha = v
        }
    override var tag: Any?
        get() = m.tag
        set(o) {
            m.tag = o
        }
}

class HuaweiMarkerOptions(markerOptions: MarkerOptions) : it.localhostsoftware.maps.model.MarkerOptions<MarkerOptions, HuaweiBitmapDescriptor, HuaweiLatLng>(markerOptions) {
    override var position: HuaweiLatLng
        get() = HuaweiLatLng(mo.position)
        set(value) {
            mo.position(value.ll)
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
    override var icon: HuaweiBitmapDescriptor?
        get() = mo.icon?.let { HuaweiBitmapDescriptor(it) }
        set(value) {
            mo.icon(value?.bitmapDescriptor)
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